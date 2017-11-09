package com.learning.datastructures.mutable.queue

import com.learning.utils.Swapper

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

case class QueueItem[T](value: T, var priority: Int)

case object EmptyQueueException extends RuntimeException("queue is empty")
case class ValueNotFoundException[T](value: T) extends RuntimeException(s"value $value was not found")
case class ValueExistsException[T](value: T) extends RuntimeException(s"value $value already exists in the queue")

class PriorityQueue[T] {
  private val queue = new ArrayBuffer[QueueItem[T]]()
  private val valueToPosition = mutable.Map[T, Int]()

  // O(logn)
  def enqueue(value: T, priority: Int): Unit = {
    valueToPosition.get(value) match {
      case Some(_) => throw ValueExistsException(value)
      case None =>
        queue.append(QueueItem(value, priority))
        valueToPosition.put(value, size - 1)
        siftUp(size - 1)
    }
  }

  // O(logn)
  def dequeue(): T = {
    if (size > 0) {
      val value = queue(0).value

      Swapper.swap(queue, 0, size - 1)
      valueToPosition.update(queue(0).value, 0)
      valueToPosition.remove(value)

      queue.remove(size - 1)
      siftDown(0)
      value
    } else {
      throw EmptyQueueException
    }
  }

  // O(logn)
  def decreasePriority(value: T, priority: Int): Unit = {
    valueToPosition.get(value) match {
      case None => throw ValueNotFoundException(value)
      case Some(position) =>
        val currentPriority = queue(position).priority
        if (currentPriority > priority) {
          queue(position).priority = priority
          siftUp(position)
        }
    }
  }

  // O(1)
  def isEmpty: Boolean = {
    queue.isEmpty
  }

  // O(1)
  def size: Int = {
    queue.size
  }

  // O(1)
  def toSeq: Seq[T] = {
    queue.map(_.value)
  }

  private def siftDown(i: Int): Unit = {
    if (i < size) {
      val e = queue(i)
      val leftIndex = 2 * i + 1
      val rightIndex = 2 * i + 2
      val left = if (leftIndex >= size) null.asInstanceOf[QueueItem[T]] else queue(leftIndex)
      val right = if (rightIndex >= size) null.asInstanceOf[QueueItem[T]] else queue(rightIndex)
      val choice = safeMin(safeMin(e, left), right)

      if (left == choice) {
        Swapper.swap(queue, i, leftIndex)
        valueToPosition.update(queue(i).value, i)
        valueToPosition.update(queue(leftIndex).value, leftIndex)

        siftDown(leftIndex)
      } else if (right == choice) {
        Swapper.swap(queue, i, rightIndex)
        valueToPosition.update(queue(i).value, i)
        valueToPosition.update(queue(rightIndex).value, rightIndex)

        siftDown(rightIndex)
      } else {
        // does nothing, because node is a leaf
      }
    }
  }

  private def siftUp(i: Int): Unit = {
    if (i > 0) {
      val e = queue(i)
      val parentI = (Math.ceil(i / 2.0) - 1).toInt
      val shouldSwap = safeMin(e, queue(parentI)) == e
      if (shouldSwap) {
        Swapper.swap(queue, i, parentI)
        valueToPosition.update(queue(i).value, i)
        valueToPosition.update(queue(parentI).value, parentI)

        siftUp(parentI)
      }
    }
  }

  private def safeMin(i1: QueueItem[T], i2: QueueItem[T]): QueueItem[T] = {
    (Option(i1), Option(i2)) match {
      case (Some(i), Some(ii)) if i.priority <= ii.priority => i
      case (Some(i), Some(ii)) if i.priority > ii.priority => ii
      case (Some(i), None) => i
      case (None, Some(ii)) => ii
      case _ => i1
    }
  }
}
