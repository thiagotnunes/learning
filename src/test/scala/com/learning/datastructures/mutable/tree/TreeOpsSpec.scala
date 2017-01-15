package com.learning.datastructures.mutable.tree

import org.specs2.mutable.Specification

class TreeOpsSpec extends Specification {
  val ops = new TreeOps

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
      ops.traverse(Some(root))(TreeOps.preOrder) ==== Seq(1, 2, 3, 4, 5)
    }

    "returns empty when root given node is None" in {
      ops.traverse(None)(TreeOps.preOrder) ==== Seq()
    }
  }

  "inOrder" >> {
    "traverses the tree in order" in {
      ops.traverse(Some(root))(TreeOps.inOrder) ==== Seq(3, 2, 1, 4, 5)
    }

    "returns empty when root given node is None" in {
      ops.traverse(None)(TreeOps.inOrder) ==== Seq()
    }
  }

  "postOrder" >> {
    "traverses the tree in post-order" in {
      ops.traverse(Some(root))(TreeOps.postOrder) ==== Seq(3, 2, 5, 4, 1)
    }

    "returns empty when root given node is None" in {
      ops.traverse(None)(TreeOps.postOrder) ==== Seq()
    }
  }
}
