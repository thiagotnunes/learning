package com.learning.datastructures.mutable.tree

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
      depthFirst.traverse(Some(root))(DepthFirst.preOrder) ==== Seq(1, 2, 3, 4, 5)
    }

    "returns empty when root given node is None" in {
      depthFirst.traverse(None)(DepthFirst.preOrder) ==== Seq()
    }
  }

  "inOrder" >> {
    "traverses the tree in order" in {
      depthFirst.traverse(Some(root))(DepthFirst.inOrder) ==== Seq(3, 2, 1, 4, 5)
    }

    "returns empty when root given node is None" in {
      depthFirst.traverse(None)(DepthFirst.inOrder) ==== Seq()
    }
  }

  "postOrder" >> {
    "traverses the tree in post-order" in {
      depthFirst.traverse(Some(root))(DepthFirst.postOrder) ==== Seq(3, 2, 5, 4, 1)
    }

    "returns empty when root given node is None" in {
      depthFirst.traverse(None)(DepthFirst.postOrder) ==== Seq()
    }
  }
}
