package com.learning.datastructures.mutable.tree.traversal

import scala.collection.mutable.ArrayBuffer

class DepthFirst {
  def traverse[T, N[_]](node: N[T])
                       (orderF: (Option[N[T]], ArrayBuffer[T]) => Seq[T])
                       (implicit ev: NodeLike[T, N]): Seq[T] = {
    orderF(Option(node), new ArrayBuffer[T]())
  }
}

object DepthFirst {
  // O(n)
  def preOrder[T, N[_]](root: Option[N[T]], buffer: ArrayBuffer[T])
                       (implicit ev: NodeLike[T, N]): Seq[T] = {
    root match {
      case None => buffer
      case Some(node) =>
        buffer += ev.e(node)
        preOrder(ev.left(node), buffer)
        preOrder(ev.right(node), buffer)
    }
  }

  // O(n)
  def inOrder[T, N[_]](root: Option[N[T]], buffer: ArrayBuffer[T])
                      (implicit ev: NodeLike[T, N]): Seq[T] = {
    root match {
      case None => buffer
      case Some(node) =>
        inOrder(ev.left(node), buffer)
        buffer += ev.e(node)
        inOrder(ev.right(node), buffer)
    }
  }

  // O(n)
  def postOrder[T, N[_]](root: Option[N[T]], buffer: ArrayBuffer[T])
                        (implicit ev: NodeLike[T, N]): Seq[T] = {
    root match {
      case None => buffer
      case Some(node) =>
        postOrder(ev.left(node), buffer)
        postOrder(ev.right(node), buffer)
        buffer += ev.e(node)
    }
  }
}
