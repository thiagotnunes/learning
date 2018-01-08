package com.learning.data_structures.mutable.queue

import scala.collection.mutable.ArrayBuffer
import scala.reflect.ClassTag

class Queue[T: ClassTag](initialCapacity: Int) {
  private var currentCapacity: Int = if (initialCapacity > 0) initialCapacity else Queue.InitialCapacity
  private var array: Array[T] = Array.ofDim[T](currentCapacity)
  private var start: Int = 0
  private var end: Int = 0
  private var count: Int = 0

  def this() {
    this(Queue.InitialCapacity)
  }

  // O(1)
  def enqueue(e: T): Unit = {
    if (isFull) {
      currentCapacity = currentCapacity * 2
      val newArray = Array.ofDim[T](currentCapacity)
      Array.copy(array, 0, newArray, 0, array.length)
      array = newArray
    } else {
      array(end) = e
      end = (end + 1) % capacity
      count = count + 1
    }
  }

  // O(1)
  def dequeue(): T = {
    if (isEmpty) {
      throw new RuntimeException("Can not dequeue from empty queue")
    } else {
      val e = array(start)
      start = (start + 1) % capacity
      count = count - 1
      e
    }
  }

  // O(1)
  def peek: T = {
    if (isEmpty) {
      throw new RuntimeException("Can not peek from empty queue")
    } else {
      array(start)
    }
  }

  // O(1)
  def size: Int = {
    count
  }

  // O(1)
  def isFull: Boolean = {
    count == capacity
  }

  // O(1)
  def isEmpty: Boolean = {
    count == 0
  }

  // O(n)
  def toSeq: Seq[T] = {
    val buffer = ArrayBuffer[T]()
    for (i <- start until (start + size)) {
      buffer += array(i % capacity)
    }
    buffer
  }

  def capacity: Int = {
    currentCapacity
  }
}

object Queue {
  val InitialCapacity: Int = 100

  def from[T: ClassTag](xs: T*): Queue[T] = {
    val queue = new Queue[T](xs.size)
    for (i <- 0 until xs.size) {
      queue.enqueue(xs(i))
    }
    queue
  }
}
