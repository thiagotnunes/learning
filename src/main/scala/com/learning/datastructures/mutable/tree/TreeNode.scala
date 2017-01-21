package com.learning.datastructures.mutable.tree

trait TreeNode[+T] {
  def e: T
  def left: Option[TreeNode[T]]
  def right: Option[TreeNode[T]]
}

