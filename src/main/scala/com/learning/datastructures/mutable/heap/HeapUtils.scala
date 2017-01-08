package com.learning.datastructures.mutable.heap

import com.learning.datastructures.mutable.array.DynamicArray

import scala.reflect.ClassTag

class HeapUtils[T] private(chooser: (T, T) => T) {
  // O(n) - looser bound is O(nlogn), but as you go down the tree there are less elements
  def heapify(array: DynamicArray[T]): Unit = {
    for (i <- (array.size / 2 - 1) to 0 by -1) {
      siftDown(i, array)
    }
  }

  // O(logn)
  def siftDown(i: Int, array: DynamicArray[T]): Unit = {
    val e = array.get(i)
    val leftIndex = 2 * i + 1
    val rightIndex = 2 * i + 2
    val left = array.get(leftIndex)
    val right = array.get(rightIndex)
    val choice = optionAwareMinOrMax(optionAwareMinOrMax(e, left), right)

    if (left == choice) {
      array.swap(i, leftIndex)
      siftDown(leftIndex, array)
    } else if (right == choice) {
      array.swap(i, rightIndex)
      siftDown(rightIndex, array)
    } else {
      // does nothing, because node is a leaf
    }
  }

  // O(logn)
  def siftUp(i: Int, array: DynamicArray[T]): Unit = {
    if (i > 0) {
      val e = array.get(i)
      val parentIndex = (Math.ceil(i / 2.0) - 1).toInt
      val shouldSwap = optionAwareMinOrMax(e, array.get(parentIndex)) == e
      if (shouldSwap) {
        array.swap(i, parentIndex)
        siftUp(parentIndex, array)
      }
    }
  }

  // O(1)
  def optionAwareMinOrMax(e1: Option[T], e2: Option[T]): Option[T] = {
    (e1, e2) match {
      case (Some(e1), Some(e2)) => Some(chooser(e1, e2))
      case (Some(e1), None) => Some(e1)
      case (None, Some(e2)) => Some(e2)
      case (None, None) => None
    }
  }
}

object HeapUtils {
  def minHeapUtils[T](implicit ev: Ordering[T]) = new HeapUtils[T](ev.min)

  def maxHeapUtils[T](implicit ev: Ordering[T]) = new HeapUtils[T](ev.max)
}