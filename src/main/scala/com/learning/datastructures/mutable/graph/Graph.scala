package com.learning.datastructures.mutable.graph

trait Graph {
  def addEdge(from: Int, to: Int, weight: Int): Boolean

  def removeEdge(from: Int, to: Int): Boolean

  def edgesFrom(from: Int): Seq[Edge]

  def numberOfVertices: Int

  def numberOfEdgesFrom(from: Int): Int
}
