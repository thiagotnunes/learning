package com.learning.data_structures.mutable.tree.bst.avl

class Rotator(heightCalculator: HeightCalculator) {
  def rotateLeft[T](node: Node[T]): Node[T] = {
    node.right match {
      case Some(rightNode) =>
        node.right = rightNode.left
        node.height = heightCalculator.fromSubtrees(node)
        rightNode.left = Some(node)
        rightNode.height = heightCalculator.fromSubtrees(rightNode)
        rightNode
      case None => node
    }
  }

  def rotateRight[T](node: Node[T]): Node[T] = {
    node.left match {
      case Some(leftNode) =>
        node.left = leftNode.right
        node.height = heightCalculator.fromSubtrees(node)
        leftNode.right = Some(node)
        leftNode.height = heightCalculator.fromSubtrees(leftNode)
        leftNode
      case None => node
    }
  }
}
