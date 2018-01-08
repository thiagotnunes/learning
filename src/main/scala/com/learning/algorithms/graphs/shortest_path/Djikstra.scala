package com.learning.algorithms.graphs.shortest_path

import com.learning.datastructures.mutable.graph.Graph
import com.learning.datastructures.mutable.queue.PriorityQueue

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

class Djikstra {

  /**
    * Djikstra algorithm does NOT work with negative weights.
    * A proper example of this can be seen here:
    * https://stackoverflow.com/questions/6799172/negative-weights-using-dijkstras-algorithm
    *
    * - V is the number of vertices in the graph
    * - E is the number of edges in the graph
    *
    * - Time Complexity - O(VlogV + ElogV)
    * - Space Complexity - O(V)
    */
  def shortestPathWithPriorityQueue(graph: Graph)(source: Int, dest: Int): (Int, Seq[Int]) = {
    val distances = mutable.Map[Int, Int]()
    val previousNode = mutable.Map[Int, Int]()
    val queue = new PriorityQueue[Int]()

    val vertices = graph.allVertices // O(V)
    vertices.foreach(v => queue.enqueue(v, Integer.MAX_VALUE)) // O(V)
    queue.decreasePriority(source, 0) // O(logV)

    vertices.foreach(v => distances.put(v, Integer.MAX_VALUE)) // O(V)
    distances.put(source, 0) // O(1)
    previousNode.remove(source)

    // O(VlogV + ElogV)
    while (!queue.isEmpty) {
      val vertex = queue.dequeue() // O(logV)
      val vertexDistance = distances(vertex)

      for (edge <- graph.edgesFrom(vertex)) { // In total E times
        val currentDistance = distances(edge.to)

        val relaxedDistance = vertexDistance + edge.weight
        if (currentDistance > relaxedDistance) {
          distances(edge.to) = relaxedDistance
          previousNode.put(edge.to, vertex)
          queue.decreasePriority(edge.to, relaxedDistance) // O(logV)
        }
      }
    }

    (
      distances(dest),
      buildPathFrom(previousNode, dest) // O(V)
    )
  }

  /**
    * - V is the number of vertices in the graph
    * - E is the number of edges in the graph
    *
    * - Time Complexity - O(V^^2 + E)
    * - Space Complexity - O(V)
    */
  def shortestPathWithoutPriorityQueue(graph: Graph)(source: Int, dest: Int): (Int, Seq[Int]) = {
    val vertices = graph.allVertices
    val distances = mutable.Map[Int, Int]()
    val previousNode = mutable.Map[Int, Int]()
    val visited = mutable.Set[Int]()

    vertices.foreach(v => distances.put(v, Integer.MAX_VALUE)) // O(V)
    distances.put(source, 0)

    // O(V)
    def nextMin(distances: mutable.Map[Int, Int], visited: mutable.Set[Int]): (Int, Int) = {
      distances.foldLeft((Integer.MAX_VALUE, Integer.MAX_VALUE)) { case (acc, (v, distance)) =>
        if (!visited.contains(v) && distance < acc._2) {
          (v, distance)
        } else {
          acc
        }
      }
    }

    // O(V^2 + E)
    for (_ <- vertices.indices) {
      val (vertex, distance) = nextMin(distances, visited) // O(V)
      visited.add(vertex) // O(1)

      // In total O(E)
      for (edge <- graph.edgesFrom(vertex)) {
        val currentDistance = distances(edge.to)
        val relaxedDistance = distance + edge.weight
        if (relaxedDistance < currentDistance) {
          previousNode(edge.to) = vertex
          distances(edge.to) = relaxedDistance
        }
      }
    }

    (
      distances(dest),
      buildPathFrom(previousNode, dest)
    )
  }

  // O(n)
  private def buildPathFrom(previousNode: mutable.Map[Int, Int], dest: Int): Seq[Int] = {
    val path = ArrayBuffer[Int]()
    var node: Option[Int] = Some(dest)
    while (node.isDefined) {
      path.append(node.get)
      node = previousNode.get(node.get)
    }
    path.reverse
  }
}
