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
        buffer += node.getE
        preOrder(node.getLeft, buffer)
        preOrder(node.getRight, buffer)
    }
  }

  // O(n)
  def inOrder[T](root: Option[TreeNode[T]], buffer: ArrayBuffer[T]): Seq[T] = {
    root match {
      case None => buffer
      case Some(node) =>
        inOrder(node.getLeft, buffer)
        buffer += node.getE
        inOrder(node.getRight, buffer)
    }
  }

  // O(n)
  def postOrder[T](root: Option[TreeNode[T]], buffer: ArrayBuffer[T]): Seq[T] = {
    root match {
      case None => buffer
      case Some(node) =>
        postOrder(node.getLeft, buffer)
        postOrder(node.getRight, buffer)
        buffer += node.getE
    }
  }
}
