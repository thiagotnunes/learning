package com.learning.data_structures.mutable.tree.bst.avl

class Rebalancer[T](rotator: Rotator, heightCalculator: HeightCalculator) {
  def rebalance(node: Node[T]): Node[T] = {
    val balancedNode = nodeBalance(node) match {
      case LeftLeft =>
        rotator.rotateRight(node)
      case LeftRight =>
        node.right = Some(rotator.rotateRight(node.right.get))
        rotator.rotateLeft(node)
      case RightLeft =>
        node.left = Some(rotator.rotateLeft(node.left.get))
        rotator.rotateRight(node)
      case RightRight =>
        rotator.rotateLeft(node)
      case Balanced =>
        node
    }

    balancedNode.height = heightCalculator.fromSubtrees(balancedNode)
    balancedNode
  }

  private def nodeBalance(node: Node[T]): NodeBalance = {
    val balance = heightCalculator.fromNode(node.left) - heightCalculator.fromNode(node.right)
    if (balance > 1) {
      if (heightCalculator.fromNode(node.left.flatMap(_.left)) >= heightCalculator.fromNode(node.left.flatMap(_.right))) {
        LeftLeft
      } else {
        RightLeft
      }
    } else if (balance < -1) {
      if (heightCalculator.fromNode(node.right.flatMap(_.left)) >= heightCalculator.fromNode(node.right.flatMap(_.right))) {
        LeftRight
      } else {
        RightRight
      }
    } else {
      Balanced
    }
  }
}
