package com.learning.algorithms.graphs.topological_sort

import com.learning.data_structures.mutable.graph.Graph

trait TopologicalSort {
  def sort(graph: Graph): Seq[Int]
}
