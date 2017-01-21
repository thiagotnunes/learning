package  com.learning.datastructures.mutable.tree


import scala.collection.mutable.ArrayBuffer

class DepthFirst {
  def traverse[T](node: Option[TreeNode[T]])(orderF: (Option[TreeNode[T]], ArrayBuffer[T]) => Seq[T]): Seq[T] = {
    orderF(node, new ArrayBuffer[T]())
  }
}

object DepthFirst {
  // O(n)
  def preOrder[T](root: Option[TreeNode[T]], buffer: ArrayBuffer[T]): Seq[T] = {
    root match {
      case None => buffer
      case Some(node) =>
        buffer += node.e
        preOrder(node.left, buffer)
        preOrder(node.right, buffer)
    }
  }

  // O(n)
  def inOrder[T](root: Option[TreeNode[T]], buffer: ArrayBuffer[T]): Seq[T] = {
    root match {
      case None => buffer
      case Some(node) =>
        inOrder(node.left, buffer)
        buffer += node.e
        inOrder(node.right, buffer)
    }
  }

  // O(n)
  def postOrder[T](root: Option[TreeNode[T]], buffer: ArrayBuffer[T]): Seq[T] = {
    root match {
      case None => buffer
      case Some(node) =>
        postOrder(node.left, buffer)
        postOrder(node.right, buffer)
        buffer += node.e
    }
  }
}
