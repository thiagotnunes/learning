package com.learning.algorithms.graphs.topological_sort

import com.learning.algorithms.graphs.DagWithCycleError
import com.learning.data_structures.mutable.graph.{AdjacencyListGraph, Graph}
import org.specs2.mutable.Specification
import org.specs2.specification.Scope

class DfsSortSpec extends Specification {

  trait Context extends Scope {
    val graph: Graph = new AdjacencyListGraph(5)
    val topologicalSort = new DfsSort
  }

  "returns the topological order of the given graph" in new Context {
    graph.addEdge(0, 1, 10)
    graph.addEdge(1, 2, 20)
    graph.addEdge(2, 3, 30)
    graph.addEdge(3, 4, 40)

    topologicalSort.sort(graph) ==== Seq(0, 1, 2, 3, 4)
  }

  "returns one of the topological orders when several are possible" in new Context {
    graph.addEdge(0, 2, 10)
    graph.addEdge(1, 2, 20)
    graph.addEdge(2, 4, 30)
    graph.addEdge(3, 2, 40)
    graph.addEdge(3, 4, 50)

    topologicalSort.sort(graph) ==== Seq(3, 1, 0, 2, 4)
  }

  "returns topological order when there are no edges" in new Context {
    topologicalSort.sort(graph) ==== Seq(4, 3, 2, 1, 0)
  }

  "returns error when there is a cycle" in new Context {
    graph.addEdge(0, 1, 10)
    graph.addEdge(1, 2, 20)
    graph.addEdge(2, 3, 30)
    graph.addEdge(3, 4, 40)
    graph.addEdge(4, 0, 50)

    topologicalSort.sort(graph) must throwA[DagWithCycleError.type]
  }
}
