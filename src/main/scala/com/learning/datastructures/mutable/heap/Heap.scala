package com.learning.datastructures.mutable.heap

import com.learning.datastructures.mutable.array.DynamicArray

import scala.reflect.ClassTag
import scala.util.Try

abstract class Heap[T: ClassTag](private val heap: DynamicArray[T], choose: (T, T) => T) {
  heapify(heap)

  // O(logn)
  def add(e: T): Unit = {
    heap.add(e)
    val i = size - 1

    def makeHeap(i: Int, e: T): Unit = {
      if (i > 0) {
        val parentIndex = (Math.ceil(i / 2.0) - 1).toInt
        val shouldSwap = nullSafeMinOrMax(e, heap.get(parentIndex)) == e
        if (shouldSwap) {
          heap.swap(i, parentIndex)
          makeHeap(parentIndex, heap.get(parentIndex))
        }
      }
    }

    makeHeap(i, e)
  }

  // O(1)
  def size: Int = {
    heap.size
  }

  // O(1)
  protected def peek: T = {
    Try(heap.get(0)).getOrElse(null.asInstanceOf[T])
  }

  // O(logn)
  protected def extract: T = {
    if (size > 0) {
      val e = heap.get(0)
      heap.swap(0, heap.size - 1)
      heap.removeLast()
      heapify(0, heap)

      e
    } else {
      null.asInstanceOf[T]
    }
  }

  // O(n) - looser bound is O(nlogn), but as you go down the tree there are less elements
  private def heapify(array: DynamicArray[T]): Unit = {
    for (i <- (array.size / 2 - 1) to 0 by -1) {
      heapify(i, array)
    }
  }

  // O(n)
  private def heapify(i: Int, array: DynamicArray[T]): Unit = {
    val e = array.get(i)
    val leftIndex = 2 * i + 1
    val rightIndex = 2 * i + 2
    val left = Try(array.get(leftIndex)).getOrElse(null.asInstanceOf[T])
    val right = Try(array.get(rightIndex)).getOrElse(null.asInstanceOf[T])
    val minOrMax = nullSafeMinOrMax(nullSafeMinOrMax(e, left), right)

    if (left == minOrMax) {
      array.swap(i, leftIndex)
      heapify(leftIndex, array)
    } else if (right == minOrMax) {
      array.swap(i, rightIndex)
      heapify(rightIndex, array)
    } else {
      // does nothing, because node is a leaf
    }
  }

  // O(1)
  val asArray: DynamicArray[T] = heap

  // O(1)
  private def nullSafeMinOrMax(e1: T, e2: T): T = {
    if (e2 == null) {
      e1
    } else if (e1 == null) {
      e2
    } else {
      choose(e1, e2)
    }
  }

  // O(n)
  override def equals(other: Any): Boolean = other match {
    case that: Heap[T] => heap.equals(that.heap)
    case _ => false
  }

  // O(n)
  override def hashCode(): Int = {
    heap.hashCode()
  }

  // O(n)
  override def toString: String = {
    val builder = new StringBuilder

    builder.append("Heap(")
    if (!heap.isEmpty) {
      for (i <- 0 until size - 1) {
        builder.append(heap.get(i))
        builder.append(",")
      }
      builder.append(heap.get(size - 1))
    }
    builder.append(")")

    builder.toString()
  }
}

class MinHeap[T: ClassTag](heap: DynamicArray[T])(implicit ev: Ordering[T]) extends Heap[T](heap, ev.min) {
  def min: T = peek
  def extractMin: T = extract
}

class MaxHeap[T: ClassTag](heap: DynamicArray[T])(implicit ev: Ordering[T]) extends Heap[T](heap, ev.max) {
}

object Heap {
  val InitialCapacity = 10
}
