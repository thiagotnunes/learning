package com.learning.datastructures.mutable.heap

import com.learning.datastructures.mutable.array.DynamicArray

import scala.reflect.ClassTag

abstract class Heap[T: ClassTag](private val heap: DynamicArray[T], choose: (T, T) => T) {
  heapify(heap)

  // O(logn)
  def add(e: T): Unit = {
    heap.add(e)
    val i = size - 1

    def bottomUpHeapify(i: Int, e: Option[T]): Unit = {
      if (i > 0) {
        val parentIndex = (Math.ceil(i / 2.0) - 1).toInt
        val shouldSwap = safeMinOrMax(e, heap.get(parentIndex)) == e
        if (shouldSwap) {
          heap.swap(i, parentIndex)
          bottomUpHeapify(parentIndex, heap.get(parentIndex))
        }
      }
    }

    bottomUpHeapify(i, Some(e))
  }

  // O(1)
  def size: Int = {
    heap.size
  }

  // O(1)
  protected def peek: Option[T] = {
    heap.get(0)
  }

  // O(logn)
  protected def extract: Option[T] = {
    if (size > 0) {
      val e = heap.get(0)
      heap.swap(0, heap.size - 1)
      heap.removeLast()
      heapify(0, heap)

      e
    } else {
      None
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
    val left = array.get(leftIndex)
    val right = array.get(rightIndex)
    val choice = safeMinOrMax(safeMinOrMax(e, left), right)

    if (left == choice) {
      array.swap(i, leftIndex)
      heapify(leftIndex, array)
    } else if (right == choice) {
      array.swap(i, rightIndex)
      heapify(rightIndex, array)
    } else {
      // does nothing, because node is a leaf
    }
  }

  // O(1)
  val asArray: DynamicArray[T] = heap

  // O(1)
  private def safeMinOrMax(e1: Option[T], e2: Option[T]): Option[T] = {
    (e1, e2) match {
      case (Some(e1), Some(e2)) => Some(choose(e1, e2))
      case (Some(e1), None) => Some(e1)
      case (None, Some(e2)) => Some(e2)
      case (None, None) => None
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
        builder.append(heap.get(i).get)
        builder.append(",")
      }
      builder.append(heap.get(size - 1).get)
    }
    builder.append(")")

    builder.toString()
  }
}

class MinHeap[T: ClassTag](heap: DynamicArray[T])(implicit ev: Ordering[T]) extends Heap[T](heap, ev.min) {
  def min: Option[T] = peek

  def extractMin: Option[T] = extract
}

class MaxHeap[T: ClassTag](heap: DynamicArray[T])(implicit ev: Ordering[T]) extends Heap[T](heap, ev.max) {
  def max: Option[T] = peek

  def extractMax: Option[T] = extract
}
