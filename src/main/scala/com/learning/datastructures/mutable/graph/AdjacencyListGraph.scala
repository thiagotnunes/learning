package com.learning.datastructures.mutable.graph

import scala.collection.mutable.ArrayBuffer
import scala.util.control.Breaks._

class AdjacencyListGraph(vertices: Int) extends Graph {
  if (vertices <= 0) {
    throw new IllegalArgumentException("graph needs at least one vertex")
  }

  private val adjacencyList = ArrayBuffer.fill(vertices)(ArrayBuffer.empty[Edge])

  // O(V)
  override def allVertices: Seq[Int] = {
    0.until(vertices)
  }

  // O(E)
  override def allEdges: Seq[Edge] = {
    val allEdges = ArrayBuffer.empty[Edge]
    for (i <- adjacencyList.indices) {
      val edges = adjacencyList(i)
      for (j <- edges.indices) {
        allEdges.append(edges(j))
      }
    }
    allEdges
  }

  // O(V)
  override def addEdge(from: Int, to: Int, weight: Int): Boolean = {
    if (isOutOfBounds(from)) {
      throw new ArrayIndexOutOfBoundsException(from)
    } else if (isOutOfBounds(to)) {
      throw new ArrayIndexOutOfBoundsException(to)
    } else {
      if (!adjacencyList(from).exists(_.to == to)) {
        adjacencyList(from).append(Edge(from, to, weight))
        true
      } else {
        false
      }
    }
  }

  // O(V)
  override def removeEdge(from: Int, to: Int): Boolean = {
    if (isOutOfBounds(from)) {
      throw new ArrayIndexOutOfBoundsException(from)
    } else if (isOutOfBounds(to)) {
      throw new ArrayIndexOutOfBoundsException(to)
    } else {
      var removed = false
      breakable {
        for (i <- adjacencyList(from).indices) {
          if (adjacencyList(from)(i).to == to) {
            adjacencyList(from).remove(i)
            removed = true
            break()
          }
        }
      }
      removed
    }
  }

  // O(1)
  override def edgesFrom(from: Int): Seq[Edge] = {
    if (isOutOfBounds(from)) {
      throw new ArrayIndexOutOfBoundsException(from)
    } else {
      adjacencyList(from)
    }
  }

  // O(1)
  override def numberOfVertices: Int = {
    vertices
  }

  // O(1)
  override def numberOfEdgesFrom(from: Int): Int = {
    if (isOutOfBounds(from)) {
      throw new ArrayIndexOutOfBoundsException(from)
    } else {
      adjacencyList(from).size
    }
  }

  // O(1)
  private def isOutOfBounds(n: Int): Boolean = {
    n < 0 || n >= numberOfVertices
  }
}
