package com.learning.data_structures.mutable.stack

import scala.collection.mutable.ArrayBuffer
import scala.reflect.ClassTag

class Stack[T: ClassTag](capacity: Int) {
  private val array: Array[T] = Array.ofDim[T](capacity)
  private var count: Int = 0

  // O(1)
  def push(e: T): Unit = {
    if (isFull) {
      throw new RuntimeException(s"Capacity of $capacity elements reached")
    } else {
      array(count) = e
      count = count + 1
    }
  }

  // O(1)
  def pop(): T = {
    if (isEmpty) {
      throw new RuntimeException("Can not pop from empty stack")
    } else {
      val e = array(count - 1)
      count = count - 1
      e
    }
  }

  // O(1)
  def peek: T = {
    if (isEmpty) {
      throw new RuntimeException("Can not peek from empty stack")
    } else {
      array(count - 1)
    }
  }

  // O(1)
  def size: Int = {
    count
  }

  // O(1)
  def isEmpty: Boolean = {
    size == 0
  }

  // O(1)
  def isFull: Boolean = {
    size == capacity
  }

  // O(n)
  def toSeq: Seq[T] = {
    val buffer = ArrayBuffer[T]()
    for (i <- (size - 1) to 0 by -1) {
      buffer += array(i)
    }
    buffer
  }
}

object Stack {
  def from[T: ClassTag](xs: T*): Stack[T] = {
    val stack = new Stack[T](xs.size)
    for (i <- 0 until xs.size) {
      stack.push(xs(i))
    }
    stack
  }
}