package com.learning.data_structures.mutable.heap

import com.learning.utils.Swapper

import scala.collection.mutable.ArrayBuffer

class BinaryHeapOps[T] private(chooser: (T, T) => T) {
  // O(n) - looser bound is O(nlogn), but as you go down the tree there are less elements
  def heapify(array: ArrayBuffer[T]): Unit = {
    for (i <- (array.size / 2 - 1) to 0 by -1) {
      siftDown(i, array)
    }
  }

  // O(logn)
  def siftDown(i: Int, array: ArrayBuffer[T], length: Int): Unit = {
    if (i < length) {
      val e = array(i)
      val leftIndex = 2 * i + 1
      val rightIndex = 2 * i + 2
      val left = if (leftIndex >= length) null.asInstanceOf[T] else array(leftIndex)
      val right = if (rightIndex >= length) null.asInstanceOf[T] else array(rightIndex)
      val choice = safeMinMax(safeMinMax(e, left), right)

      if (left == choice) {
        Swapper.swap(array, i, leftIndex)
        siftDown(leftIndex, array, length)
      } else if (right == choice) {
        Swapper.swap(array, i, rightIndex)
        siftDown(rightIndex, array, length)
      } else {
        // does nothing, because node is a leaf
      }
    }
  }

  // O(logn)
  def siftDown(i: Int, array: ArrayBuffer[T]): Unit = {
    siftDown(i, array, array.size)
  }

  // O(logn)
  def siftUp(i: Int, array: ArrayBuffer[T]): Unit = {
    if (i > 0) {
      val e = array(i)
      val parentIndex = (Math.ceil(i / 2.0) - 1).toInt
      val shouldSwap = safeMinMax(e, array(parentIndex)) == e
      if (shouldSwap) {
        Swapper.swap(array, i, parentIndex)
        siftUp(parentIndex, array)
      }
    }
  }

  // O(1)
  def safeMinMax(e1: T, e2: T): T = {
    if (e1 == null) {
      e2
    } else if (e2 == null) {
      e1
    } else {
      chooser(e1, e2)
    }
  }
}

object BinaryHeapOps {
  def minHeapUtils[T](implicit ev: Ordering[T]) = new BinaryHeapOps[T](ev.min)

  def maxHeapUtils[T](implicit ev: Ordering[T]) = new BinaryHeapOps[T](ev.max)
}