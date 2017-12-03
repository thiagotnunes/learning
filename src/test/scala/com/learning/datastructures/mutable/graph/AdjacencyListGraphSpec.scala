package com.learning.datastructures.mutable.graph

import org.specs2.mutable.Specification
import org.specs2.specification.Scope

class AdjacencyListGraphSpec extends Specification {

  trait Context extends Scope {
    val graph = new AdjacencyListGraph(10)
  }

  "creation" >> {
    "creates a graph with the given vertices" in {
      val graph = new AdjacencyListGraph(10)

      graph.numberOfVertices ==== 10
      graph.edgesFrom(0) ==== Seq()
    }

    "throws an exception when creating a graph with negative vertices" in {
      new AdjacencyListGraph(-1) must throwA[IllegalArgumentException]
    }
  }

  "addEdge" >> {
    "adds an edge between two nodes" in new Context {
      graph.addEdge(0, 1, 10) ==== true

      graph.edgesFrom(0) ==== Seq(Edge(0, 1, 10))
    }

    "returns false when adding duplicate edge" in new Context {
      graph.addEdge(0, 1, 10)

      graph.addEdge(0, 1, 20) ==== false
      graph.edgesFrom(0) ==== Seq(Edge(0, 1, 10))
    }

    "throws an exception when trying to add edge from non-existing node" in new Context {
      graph.addEdge(10, 0, 30) must throwA[ArrayIndexOutOfBoundsException]
    }

    "throws an exception when trying to add edge to non-existing node" in new Context {
      graph.addEdge(0, 10, 30) must throwA[ArrayIndexOutOfBoundsException]
    }
  }

  "allEdges" >> {
    "returns all the edges in the graph" in new Context {
      graph.addEdge(0, 1, 10)
      graph.addEdge(0, 2, 20)
      graph.addEdge(0, 4, 40)
      graph.addEdge(1, 2, 80)
      graph.addEdge(2, 3, 160)
      graph.addEdge(2, 4, 320)

      graph.allEdges ==== Seq(
        Edge(0, 1, 10),
        Edge(0, 2, 20),
        Edge(0, 4, 40),
        Edge(1, 2, 80),
        Edge(2, 3, 160),
        Edge(2, 4, 320)
      )
    }

    "returns empty array when there are no edges" in new Context {
      graph.allEdges ==== Seq()
    }
  }


  "edgesFrom" >> {
    "returns all edges from the given node" in new Context {
      graph.addEdge(0, 1, 10)
      graph.addEdge(0, 2, 20)
      graph.addEdge(0, 4, 40)

      graph.edgesFrom(0) ==== Seq(Edge(0, 1, 10), Edge(0, 2, 20), Edge(0, 4, 40))
    }

    "throws an exception when given node is out of bounds" in new Context {
      graph.edgesFrom(10) must throwA[ArrayIndexOutOfBoundsException]
    }
  }

  "numberOfEdgesFrom" >> {
    "returns the number of edges from a given vertex" in new Context {
      graph.addEdge(0, 1, 10)
      graph.addEdge(0, 2, 20)
      graph.addEdge(1, 2, 30)
      graph.addEdge(2, 0, 40)

      graph.numberOfEdgesFrom(0) ==== 2
    }

    "throws an exception when given node is out of bounds" in new Context {
      graph.numberOfEdgesFrom(10) must throwA[ArrayIndexOutOfBoundsException]
    }
  }

  "removeEdge" >> {
    "removes an existing edge" in new Context {
      graph.addEdge(0, 1, 10)
      graph.addEdge(0, 2, 20)
      graph.addEdge(0, 3, 30)

      graph.removeEdge(0, 2) ==== true

      graph.edgesFrom(0) ==== Seq(Edge(0, 1, 10), Edge(0, 3, 30))
    }

    "returns false when no edge was removed" in new Context {
      graph.removeEdge(0, 1) ==== false
    }

    "throws an exception when trying to remove from non-existing node" in new Context {
      graph.removeEdge(10, 0) must throwA[ArrayIndexOutOfBoundsException]
    }

    "throws an exception when trying to remove to non-existing node" in new Context {
      graph.removeEdge(0, 10) must throwA[ArrayIndexOutOfBoundsException]
    }
  }
}
