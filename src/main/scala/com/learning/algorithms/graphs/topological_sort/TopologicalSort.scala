package com.learning.algorithms.graphs.topological_sort

import com.learning.datastructures.mutable.graph.Graph

trait TopologicalSort {
  def sort(graph: Graph): Seq[Int]
}
