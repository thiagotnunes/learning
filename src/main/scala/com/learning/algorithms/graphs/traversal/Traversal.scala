package com.learning.algorithms.graphs.traversal

import com.learning.data_structures.mutable.graph.{Edge, Graph}

trait Traversal {
  def traverse(graph: Graph)(from: Int): Seq[Int] = {
    traverseWithSideEffects(graph)(
      from,
      (_: Int) => (),
      (_: Edge) => (),
      (_: Int) => ()
    )
  }

  def traverseWithSideEffects(graph: Graph)
                             (from: Int,
                              processNodeEarly: (Int) => Unit,
                              processEdge: (Edge) => Unit,
                              processNodeLate: (Int) => Unit): Seq[Int]
}
