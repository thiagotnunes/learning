package com.learning.algorithms.graphs.traversal

import com.learning.data_structures.mutable.graph.{Edge, Graph}

/**
  * - V is the number of vertices in the graph
  * - E is the number of edges in the graph
  *
  * Time complexity - O(V + E)
  * Space complexity - O(E) due to the recursive calls for each edge
  */
class DepthFirst extends Traversal {
  override def traverseWithSideEffects(graph: Graph)
                                      (from: Int,
                                       processNodeEarly: (Int) => Unit,
                                       processEdge: (Edge) => Unit,
                                       processNodeLate: (Int) => Unit): Seq[Int] = {

    def go(node: Int, visited: Set[Int], order: Seq[Int]): (Set[Int], Seq[Int]) = {
      if (visited.contains(node)) {
        (visited, order)
      } else {
        processNodeEarly(node)

        val edges = graph.edgesFrom(node)

        val (newVisited, newOrder) = edges
          .foldLeft((visited + node, order :+ node)) { case ((v, o), edge) =>
            processEdge(edge)
            go(edge.to, v, o)
          }

        processNodeLate(node)

        (newVisited, newOrder)
      }
    }

    go(from, Set[Int](), Array[Int]())._2
  }
}
