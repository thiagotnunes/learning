package com.learning.datastructures.mutable.tree.bst.avl

class HeightCalculator {
  def fromSubtrees[T](node: Node[T]): Int = {
    Math.max(
      fromNode(node.left),
      fromNode(node.right)
    ) + 1
  }

  private def fromNode[T](node: Option[Node[T]]): Int = {
    node match {
      case Some(Node(_, _, _, height)) => height
      case None => 0
    }
  }
}
