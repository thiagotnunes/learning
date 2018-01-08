package com.learning.algorithms.tree.traversal

import com.learning.datastructures.mutable.tree.bst.unbalanced.Node
import org.specs2.mutable.Specification

class BreadthFirstSpec extends Specification {
  val breadthFirst = new BreadthFirst

  // Note: This is not a BST
  //     1
  //   2   4
  //  3 - - 5
  val root = Node(
    1,
    Some(Node(
      2,
      Some(Node.leaf(3)),
      None
    )),
    Some(Node(
      4,
      None,
      Some(Node.leaf(5))
    ))
  )

  "traverses the tree level by level" in {
    breadthFirst.traverse(root) ==== Seq(1, 2, 4, 3, 5)
  }
}
