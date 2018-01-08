package com.learning.data_structures.mutable.tree.bst.avl

case class Node[T](var e: T,
                   var left: Option[Node[T]],
                   var right: Option[Node[T]],
                   var height: Int)

object Node {
  def leaf[T](e: T): Node[T] = {
    Node(e, None, None, 1)
  }
}
