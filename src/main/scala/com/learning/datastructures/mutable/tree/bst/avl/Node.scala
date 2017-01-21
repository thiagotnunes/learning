package com.learning.datastructures.mutable.tree.bst.avl

import com.learning.datastructures.mutable.tree.TreeNode

case class Node[T](e: T,
                   var left: Option[Node[T]],
                   var right: Option[Node[T]],
                   var height: Int) extends TreeNode[T]

object Node {
  def leaf[T](e: T): Node[T] = {
    Node(e, None, None, 1)
  }
}
