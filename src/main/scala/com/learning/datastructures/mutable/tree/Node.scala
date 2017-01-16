package com.learning.datastructures.mutable.tree

case class Node[T](e: T, var left: Option[Node[T]], var right: Option[Node[T]])

object Node {
  def leaf[T](e: T): Node[T] = {
    Node(e, None, None)
  }
}