package com.learning.algorithms.tree.traversal

import com.learning.datastructures.mutable.tree.bst.unbalanced.Node
import org.specs2.mutable.Specification

class DepthFirstSpec extends Specification {
  val depthFirst = new DepthFirst

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

  "preOrder" >> {
    "traverses the tree in pre-order" in {
      depthFirst.traverse(root)(DepthFirst.preOrder[Int, Node]) ==== Seq(1, 2, 3, 4, 5)
    }
  }

  "inOrder" >> {
    "traverses the tree in order" in {
      depthFirst.traverse(root)(DepthFirst.inOrder[Int, Node]) ==== Seq(3, 2, 1, 4, 5)
    }
  }

  "postOrder" >> {
    "traverses the tree in post-order" in {
      depthFirst.traverse(root)(DepthFirst.postOrder[Int, Node]) ==== Seq(3, 2, 5, 4, 1)
    }
  }
}
