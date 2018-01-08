package com.learning.algorithms.graphs.shortest_path

import com.learning.datastructures.mutable.graph.{AdjacencyListGraph, Graph}
import org.specs2.mutable.Specification
import org.specs2.specification.Scope

class DjikstraSpec extends Specification {

  trait Context extends Scope {
    val graph: Graph = new AdjacencyListGraph(9)
    val djikstra = new Djikstra

    graph.addEdge(0, 1, 4)
    graph.addEdge(0, 7, 8)

    graph.addEdge(1, 0, 4)
    graph.addEdge(1, 2, 8)
    graph.addEdge(1, 7, 11)

    graph.addEdge(2, 1, 8)
    graph.addEdge(2, 3, 7)
    graph.addEdge(2, 5, 4)
    graph.addEdge(2, 8, 2)

    graph.addEdge(3, 2, 7)
    graph.addEdge(3, 4, 9)
    graph.addEdge(3, 5, 14)

    graph.addEdge(4, 3, 9)
    graph.addEdge(4, 5, 10)

    graph.addEdge(5, 2, 4)
    graph.addEdge(5, 3, 14)
    graph.addEdge(5, 4, 10)

    graph.addEdge(6, 5, 2)
    graph.addEdge(6, 7, 1)
    graph.addEdge(6, 8, 6)

    graph.addEdge(7, 0, 8)
    graph.addEdge(7, 1, 11)
    graph.addEdge(7, 6, 1)
    graph.addEdge(7, 8, 7)

    graph.addEdge(8, 2, 2)
    graph.addEdge(8, 6, 6)
    graph.addEdge(8, 7, 7)
  }

  "using priority queues" in {
    "returns the shortest path from source to destination" in new Context {
      djikstra.shortestPathWithPriorityQueue(graph)(0, 4) ==== (21, Seq(0, 7, 6, 5, 4))
    }
  }

  "without priority queues" in {
    "returns the shortest path from source to destination" in new Context {
      djikstra.shortestPathWithoutPriorityQueue(graph)(0, 8) ==== (14, Seq(0, 1, 2, 8))
    }
  }

}
