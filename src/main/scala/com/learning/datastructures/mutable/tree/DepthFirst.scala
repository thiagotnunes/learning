package  com.learning.datastructures.mutable.tree

import scala.collection.mutable.ArrayBuffer

class DepthFirst {
  def traverse[T](node: Option[Node[T]])(orderF: (Option[Node[T]], ArrayBuffer[T]) => Seq[T]): Seq[T] = {
    orderF(node, new ArrayBuffer[T]())
  }
}

object DepthFirst {
  // O(n)
  def preOrder[T](node: Option[Node[T]], buffer: ArrayBuffer[T]): Seq[T] = {
    node match {
      case None => buffer
      case Some(Node(e, left, right)) =>
        buffer += e
        preOrder(left, buffer)
        preOrder(right, buffer)
    }
  }

  // O(n)
  def inOrder[T](node: Option[Node[T]], buffer: ArrayBuffer[T]): Seq[T] = {
    node match {
      case None => buffer
      case Some(Node(e, left, right)) =>
        inOrder(left, buffer)
        buffer += e
        inOrder(right, buffer)
    }
  }

  // O(n)
  def postOrder[T](node: Option[Node[T]], buffer: ArrayBuffer[T]): Seq[T] = {
    node match {
      case None => buffer
      case Some(Node(e, left, right)) =>
        postOrder(left, buffer)
        postOrder(right, buffer)
        buffer += e
    }
  }
}
