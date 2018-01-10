package com.learning.algorithms.graphs.topological_sort
import com.learning.algorithms.graphs.traversal.DepthFirst
import com.learning.data_structures.mutable.graph.Graph

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

/**
  * This implementation does NOT detect cycles, so if there are
  * any, the result will be incorrect.
  *
  * - V is the number of vertices in the graph
  * - E is the number of edges in the graph
  *
  * Time complexity - O(V + E)
  * Space complexity - O(E) due to dfs
  */
class DfsSort extends TopologicalSort {
  override def sort(graph: Graph): Seq[Int] = {
    val topologicalSort = ListBuffer[Int]() // O(V)
    val visited = mutable.Set[Int]() // O(V)
    val traversal = new DepthFirst

    val vertices = graph.allVertices

    vertices.foreach(v => {
      if (!visited.contains(v)) {
        traversal.traverseWithSideEffects(graph)(
          v,
          _ => (),
          _ => (),
          node => {
            if (!visited.contains(node)) {
              visited.add(node)
              topologicalSort.prepend(node)
            }
          }
        )
      }
    })

    topologicalSort
  }
}
