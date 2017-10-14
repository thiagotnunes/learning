package com.learning.datastructures.mutable.sets

import com.learning.datastructures.mutable.sets.UnionFindDisjointSets.SetNode

import scala.collection.mutable

/**
  * Used to keep track of which elements belong to each set.
  */
class UnionFindDisjointSets {

  private val elements = mutable.HashMap[Int, SetNode]()

  // O(1)
  def makeSet(i: Int): Boolean = {
    if (!elements.contains(i)) {
      val e = SetNode(i, 0, None)
      elements(i) = e
      true
    } else {
      false
    }
  }

  // O(1)
  def findSet(i: Int): Option[SetNode] = {
    elements.get(i) match {
      case None => None
      case Some(node) if node.parent.isEmpty => Some(node)
      case Some(node@SetNode(_, _, Some(parent))) =>
        val nodeParent = findSet(parent.data)
        node.parent = nodeParent
        nodeParent
    }
  }

  // O(1)
  def union(i: Int, j: Int): Boolean = {
    (findSet(i), findSet(j)) match {
      case (Some(parentI), Some(parentJ)) =>
        union(parentI, parentJ)
        true
      case _ => false
    }
  }

  def sets: Iterable[SetNode] = {
    elements.values
  }

  private def union(parent1: SetNode, parent2: SetNode): Unit = {
    if (parent1 != parent2) {
      if (parent1.rank == parent2.rank) {
        parent1.rank = parent1.rank + 1
        parent2.parent = Some(parent1)
      } else if (parent1.rank > parent2.rank) {
        parent2.rank = 0
        parent2.parent = Some(parent1)
      } else {
        parent1.rank = 0
        parent1.parent = Some(parent2)
      }
    }
  }
}

object UnionFindDisjointSets {
  case class SetNode(data: Int, private[sets] var rank: Int, private[sets] var parent: Option[SetNode])
}
