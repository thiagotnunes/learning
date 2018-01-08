package com.learning.algorithms.tree.traversal

import com.learning.data_structures.mutable.tree.bst.avl.Node
import com.learning.data_structures.mutable.tree.bst.unbalanced

trait NodeLike[T, N[_]] {
  def e(n: N[T]): T

  def left(n: N[T]): Option[N[T]]

  def right(n: N[T]): Option[N[T]]
}

// This is way more expensive than simple interface / implementation or
// "implicit val", but it decouples the code, so I am going with it for now
object NodeLike {
  implicit def unbalancedNodeLike[T] = new NodeLike[T, unbalanced.Node] {
    override def e(n: unbalanced.Node[T]): T = n.e

    override def left(n: unbalanced.Node[T]): Option[unbalanced.Node[T]] = n.left

    override def right(n: unbalanced.Node[T]): Option[unbalanced.Node[T]] = n.right
  }

  implicit def avlNodeLike[T] = new NodeLike[T, Node] {
    override def e(n: Node[T]): T = n.e

    override def left(n: Node[T]): Option[Node[T]] = n.left

    override def right(n: Node[T]): Option[Node[T]] = n.right
  }
}
