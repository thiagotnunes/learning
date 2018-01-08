package com.learning.data_structures.mutable.graph

trait Graph {
  def allVertices: Seq[Int]

  def allEdges: Seq[Edge]

  def addEdge(from: Int, to: Int, weight: Int): Boolean

  def removeEdge(from: Int, to: Int): Boolean

  def edgesFrom(from: Int): Seq[Edge]

  def numberOfVertices: Int

  def numberOfEdgesFrom(from: Int): Int

  def inDegree(from: Int): Int

  def outDegree(from: Int): Int
}
