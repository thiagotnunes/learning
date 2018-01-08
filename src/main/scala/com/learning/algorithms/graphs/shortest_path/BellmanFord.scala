package com.learning.algorithms.graphs.shortest_path

import com.learning.data_structures.mutable.graph.Graph

import scala.collection.mutable.ArrayBuffer

/**
  * Bellman-Ford algorithm does NOT work with negative cycles,
  * but it does with negative edges.
  *
  * - V is the number of vertices in the graph
  * - E is the number of edges in the graph
  *
  * Time Complexity - O(VE)
  * Space Complexity - O(V)
  */
class BellmanFord {
  def shortestPath(graph: Graph)(source: Int, dest: Int): (Int, Seq[Int]) = {
    val distances = collection.mutable.Map[Int, Int]()
    val previousNode = collection.mutable.Map[Int, Int]()
    val vertices = graph.allVertices
    val edges = graph.allEdges

    vertices.foreach(v => {
      distances.put(v, Integer.MAX_VALUE)
      previousNode(v) = Integer.MAX_VALUE
    })
    distances.put(source, 0)
    previousNode.put(source, Integer.MAX_VALUE)

    for (_ <- 0.until(vertices.size - 1)) {
      for (j <- edges.indices) {
        val from = edges(j).from
        val to = edges(j).to
        val weight = edges(j).weight

        if (distances(from) < Integer.MAX_VALUE && distances(to) > distances(from) + weight) {
          distances.put(to, distances(from) + weight)
          previousNode.put(to, from)
        }
      }
    }

    (
      distances(dest),
      buildPath(dest, previousNode)
    )
  }

  // O(V)
  private def buildPath(dest: Int, previousNode: collection.mutable.Map[Int, Int]): Seq[Int] = {
    var current = dest
    val path = ArrayBuffer[Int]()
    do {
      path.append(current)
      current = previousNode(current)
    } while (current != Integer.MAX_VALUE)

    path.reverse
  }
}
