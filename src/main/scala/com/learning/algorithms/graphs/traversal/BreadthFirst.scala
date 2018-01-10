package com.learning.algorithms.graphs.traversal

import com.learning.data_structures.mutable.graph.{Edge, Graph}
import com.learning.data_structures.mutable.queue.Queue

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

/**
  * - V is the number of vertices in the graph
  * - E is the number of edges in the graph
  *
  * Time complexity - O(V + E)
  * Space complexity - O(V)
  */
class BreadthFirst extends Traversal {
  override def traverseWithSideEffects(graph: Graph)
                                      (from: Int,
                                       processNodeEarly: (Int) => Unit,
                                       processEdge: (Edge) => Unit,
                                       processNodeLate: (Int) => Unit): Seq[Int] = {
    val queue = new Queue[Int](graph.numberOfVertices) // O(V)
    val visited = mutable.Set[Int]() // this could be an array of booleans also
    val order = ArrayBuffer[Int]()

    queue.enqueue(from) // O(1)

    // O(V + E)
    while (!queue.isEmpty) {
      val node = queue.dequeue() // O(1)

      order += node
      visited.add(node)

      processNodeEarly(node)

      // O(E)
      graph
        .edgesFrom(node)
        .foreach(edge => {
          if (!visited.contains(edge.to)) {
            queue.enqueue(edge.to)
          }
          processEdge(edge)
        })

      processNodeLate(node)
    }

    order
  }
}
