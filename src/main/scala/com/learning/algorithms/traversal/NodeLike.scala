package com.learning.algorithms.traversal

import com.learning.datastructures.mutable.tree.bst.avl.{Node => AvlNode}
import com.learning.datastructures.mutable.tree.bst.unbalanced.{Node => UnbalancedNode}

trait NodeLike[T, N[_]] {
  def e(n: N[T]): T

  def left(n: N[T]): Option[N[T]]

  def right(n: N[T]): Option[N[T]]
}

// This is way more expensive than simple interface / implementation or
// "implicit val", but it decouples the code, so I am going with it for now
object NodeLike {
  implicit def unbalancedNodeLike[T] = new NodeLike[T, UnbalancedNode] {
    override def e(n: UnbalancedNode[T]): T = n.e

    override def left(n: UnbalancedNode[T]): Option[UnbalancedNode[T]] = n.left

    override def right(n: UnbalancedNode[T]): Option[UnbalancedNode[T]] = n.right
  }

  implicit def avlNodeLike[T] = new NodeLike[T, AvlNode] {
    override def e(n: AvlNode[T]): T = n.e

    override def left(n: AvlNode[T]): Option[AvlNode[T]] = n.left

    override def right(n: AvlNode[T]): Option[AvlNode[T]] = n.right
  }
}
