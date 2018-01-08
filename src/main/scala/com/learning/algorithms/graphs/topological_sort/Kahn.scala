package com.learning.algorithms.graphs.topological_sort

import com.learning.algorithms.graphs.DagWithCycleError
import com.learning.datastructures.mutable.graph.Graph
import com.learning.datastructures.mutable.queue.Queue

import scala.collection.mutable.ArrayBuffer

/**
  * - V is the number of vertices in the graph
  * - E is the number of edges in the graph
  *
  * Time Complexity - O(V + E)
  * Space Complexity - O(V)
  */
class Kahn extends TopologicalSort {

  def sort(graph: Graph): Seq[Int] = {
    val numberOfVertices = graph.numberOfVertices // O(1)
    val topologicalOrder = new ArrayBuffer[Int](numberOfVertices) // O(1)
    val inDegrees = ArrayBuffer.fill(numberOfVertices)(0) // O(V)
    val queue = new Queue[Int](numberOfVertices) // O(1)

    // Initialize inDegrees
    graph.allEdges.foreach(edge => inDegrees(edge.to) = inDegrees(edge.to) + 1) // O(E)

    // Adds all the nodes with inDegree 0 to the queue
    inDegrees
      .zipWithIndex // O(V)
      .filter { case (inDegree, _) => inDegree == 0 } // O(V)
      .map(_._2) // O(V)
      .foreach(queue.enqueue) // O(V)

    // O(V + E)
    while (!queue.isEmpty) {
      val v = queue.dequeue()

      topologicalOrder += v

      graph.edgesFrom(v).foreach(edge => {
        val to = edge.to
        inDegrees(to) = inDegrees(to) - 1
        if (inDegrees(to) == 0) {
          queue.enqueue(to)
        }
      })
    }

    if (topologicalOrder.size != numberOfVertices) {
      throw DagWithCycleError
    } else {
      topologicalOrder
    }
  }
}
