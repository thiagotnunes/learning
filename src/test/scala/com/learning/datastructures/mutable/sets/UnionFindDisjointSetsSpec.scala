package com.learning.datastructures.mutable.sets

import com.learning.datastructures.mutable.sets.UnionFindDisjointSets.SetNode
import org.specs2.mutable.Specification
import org.specs2.specification.Scope

class UnionFindDisjointSetsSpec extends Specification {

  trait Context extends Scope {
    val sets: UnionFindDisjointSets = new UnionFindDisjointSets
  }

  "makeSet" >> {
    "makeSet makes set by setting the parent to be None and rank to be 0" in new Context {
      sets.makeSet(0) ==== true

      sets.sets ==== Seq(SetNode(0, 0, None))
    }

    "makeSet does nothing if set already exists" in new Context {
      sets.makeSet(0) ==== true
      sets.makeSet(0) ==== false
      sets.sets ==== Seq(SetNode(0, 0, None))
    }
  }

  "findSet" >> {
    "returns the node when it is found" in new Context {
      sets.makeSet(0)

      sets.findSet(0) ==== Some(SetNode(0, 0, None))
    }

    "returns None, when set does not exist for the given input" in new Context {
      sets.findSet(0) ==== None
    }
  }

  "union" >> {
    "unions 2 sets with the same rank by setting parent to be the same and incrementing parent rank" in new Context {
      sets.makeSet(0)
      sets.makeSet(1)

      sets.union(0, 1)

      private val expectedParent = SetNode(0, 1, None)
      sets.findSet(0) ==== Some(expectedParent)
      sets.findSet(1) ==== Some(expectedParent)
    }

    "unions 2 sets when the first set has a greater rank than the second one" in new Context {
      sets.makeSet(0)
      sets.makeSet(1)
      sets.makeSet(2)

      sets.union(0, 1)
      sets.union(1, 2)

      private val expectedParent = SetNode(0, 1, None)
      sets.findSet(0) ==== Some(expectedParent)
      sets.findSet(1) ==== Some(expectedParent)
      sets.findSet(2) ==== Some(expectedParent)
    }

    "unions 2 sets when the second set has a greater rank than the first one" in new Context {
      sets.makeSet(0)
      sets.makeSet(1)
      sets.makeSet(2)

      sets.union(1, 2)
      sets.union(0, 2)

      private val expectedParent = SetNode(1, 1, None)
      sets.findSet(0) ==== Some(expectedParent)
      sets.findSet(1) ==== Some(expectedParent)
      sets.findSet(2) ==== Some(expectedParent)
    }

    "does nothing when the second set does not exist" in new Context {
      sets.makeSet(0)

      sets.union(0, 1)

      sets.findSet(0) ==== Some(SetNode(0, 0, None))
    }

    "does nothing when the first set does not exist" in new Context {
      sets.makeSet(1)

      sets.union(0, 1)

      sets.findSet(1) ==== Some(SetNode(1, 0, None))
    }
  }

  "compresses the path during findSet operation" in new Context {
    sets.makeSet(0)
    sets.makeSet(1)
    sets.makeSet(2)
    sets.makeSet(3)

    sets.union(0, 1)
    sets.union(2, 3)
    sets.union(0, 3)

    sets.findSet(3) ==== Some(SetNode(0, 2, None))
    sets.sets.find(_.data == 3).flatMap(_.parent) ==== Some(SetNode(0, 2, None))
  }
}
