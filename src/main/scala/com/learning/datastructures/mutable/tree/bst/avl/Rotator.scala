package com.learning.datastructures.mutable.tree.bst.avl

class Rotator {
  def rotateLeft[T](node: Node[T]): Node[T] = {
    node.right match {
      case Some(rightNode) =>
        node.right = rightNode.left
        node.height = heightFor(node)
        rightNode.left = Some(node)
        rightNode.height = heightFor(rightNode)
        rightNode
      case None => node
    }
  }

  def rotateRight[T](node: Node[T]): Node[T] = {
    node.left match {
      case Some(leftNode) =>
        node.left = leftNode.right
        node.height = heightFor(node)
        leftNode.right = Some(node)
        leftNode.height = heightFor(leftNode)
        leftNode
      case None => node
    }
  }

  private def heightFor[T](node: Node[T]): Int = {
    Math.max(node.left.map(_.height).getOrElse(0), node.right.map(_.height).getOrElse(0)) + 1
  }
}
