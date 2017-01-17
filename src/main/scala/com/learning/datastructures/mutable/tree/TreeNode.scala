package com.learning.datastructures.mutable.tree

trait TreeNode[T] {
  val getE: T
  val getLeft: Option[TreeNode[T]]
  val getRight: Option[TreeNode[T]]
}

