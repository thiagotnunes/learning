package com.learning.datastructures.mutable.tree

import com.learning.datastructures.mutable.queue.Queue

import scala.collection.mutable.ArrayBuffer

class BreadthFirst {
  def traverse[T](root: Option[TreeNode[T]]): Seq[T] = {
    val queue = new Queue[TreeNode[T]]()
    val result = new ArrayBuffer[T]()

    root.foreach(queue.enqueue)
    while (!queue.isEmpty) {
      val node = queue.dequeue()
      result += node.e
      node.left.foreach(queue.enqueue)
      node.right.foreach(queue.enqueue)
    }

    result
  }
}
