package com.learning.datastructures.mutable.graph

import com.learning.datastructures.mutable.graph.AdjacencyMatrixGraph.NoEdge

import scala.collection.mutable.ArrayBuffer

class AdjacencyMatrixGraph(vertices: Int) extends Graph {
  if (vertices <= 0) {
    throw new IllegalArgumentException("graph needs at least one vertex")
  }

  private val adjacencyMatrix = ArrayBuffer.fill(vertices, vertices)(NoEdge)

  // O(1)
  override def addEdge(from: Int, to: Int, weight: Int): Boolean = {
    if (!isValidWeight(weight)) {
      throw new IllegalArgumentException(s"weight must be greater than $weight")
    } else if (isOutOfBounds(from)) {
      throw new ArrayIndexOutOfBoundsException(from)
    } else if (isOutOfBounds(to)) {
      throw new ArrayIndexOutOfBoundsException(to)
    } else if (adjacencyMatrix(from)(to) == NoEdge) {
      adjacencyMatrix(from)(to) = weight
      true
    } else {
      false
    }
  }

  // O(1)
  override def removeEdge(from: Int, to: Int): Boolean = {
    if (isOutOfBounds(from)) {
      throw new ArrayIndexOutOfBoundsException(from)
    } else if (isOutOfBounds(to)) {
      throw new ArrayIndexOutOfBoundsException(to)
    } else if (adjacencyMatrix(from)(to) > NoEdge) {
      adjacencyMatrix(from)(to) = NoEdge
      true
    } else {
      false
    }
  }

  // O(V)
  override def edgesFrom(from: Int): Seq[Edge] = {
    if (isOutOfBounds(from)) {
      throw new ArrayIndexOutOfBoundsException(from)
    } else {
      adjacencyMatrix(from)
        .zipWithIndex
        .filter { case (weight, _) => weight > NoEdge }
        .map { case (weight, to) => Edge(to, weight) }
    }
  }

  // O(1)
  override def numberOfVertices: Int = {
    vertices
  }

  // O(V)
  override def numberOfEdgesFrom(from: Int): Int = {
    if (isOutOfBounds(from)) {
      throw new ArrayIndexOutOfBoundsException(from)
    } else {
      adjacencyMatrix(from).count(_ > NoEdge)
    }
  }

  // O(1)
  private def isOutOfBounds(n: Int): Boolean = {
    n < 0 || n >= numberOfVertices
  }

  private def isValidWeight(weight: Int): Boolean = {
    weight > NoEdge
  }
}

object AdjacencyMatrixGraph {
  val NoEdge: Int = Integer.MIN_VALUE
}
