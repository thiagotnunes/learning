package com.learning.algorithms.traversal

import com.learning.datastructures.mutable.queue.Queue

import scala.collection.mutable.ArrayBuffer
import scala.reflect.ClassTag

class BreadthFirst {
  def traverse[T, N[_]](root: N[T])
                       (implicit ev: NodeLike[T, N], c: ClassTag[N[T]]): Seq[T] = {
    val queue = new Queue[N[T]]()
    val result = new ArrayBuffer[T]()

    queue.enqueue(root)
    while (!queue.isEmpty) {
      val node = queue.dequeue()
      result += ev.e(node)
      ev.left(node).foreach(queue.enqueue)
      ev.right(node).foreach(queue.enqueue)
    }

    result
  }
}
