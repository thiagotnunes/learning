package com.learning.datastructures.mutable.heap

import com.learning.utils.Swapper

import scala.collection.mutable.ArrayBuffer
import scala.util.Try


class BinaryHeap[T] private(array: ArrayBuffer[T], heapUtils: BinaryHeapOps[T]) {
  private val heap: ArrayBuffer[T] = array

  // O(logn)
  def add(e: T): Unit = {
    heap(heap.length - 1) = e
    val i = size - 1

    heapUtils.siftUp(i, heap)
  }

  // O(1)
  def size: Int = {
    heap.length
  }

  // O(1)
  def peek: Option[T] = {
    Try(heap(0)).toOption
  }

  // O(logn)
  def extract: Option[T] = {
    if (size > 0) {
      val e = Try(heap(0)).toOption
      Swapper.swap(heap, 0, heap.length - 1)
      heap.remove(heap.length - 1)
      heapUtils.siftDown(0, heap)

      e
    } else {
      None
    }
  }

  // O(1)
  val asArray: ArrayBuffer[T] = heap

  // O(n)
  override def equals(other: Any): Boolean = other match {
    case that: BinaryHeap[T] => heap.equals(that.heap)
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
    if (heap.nonEmpty) {
      for (i <- 0 until size - 1) {
        builder.append(heap(i))
        builder.append(",")
      }
      builder.append(heap(size - 1))
    }
    builder.append(")")

    builder.toString()
  }
}

object BinaryHeap {
  def minHeap[T: Ordering](array: ArrayBuffer[T]): BinaryHeap[T] = {
    BinaryHeapOps.minHeapUtils.heapify(array)
    new BinaryHeap[T](array, BinaryHeapOps.minHeapUtils[T])
  }

  def maxHeap[T: Ordering](array: ArrayBuffer[T]): BinaryHeap[T] = {
    BinaryHeapOps.maxHeapUtils.heapify(array)
    new BinaryHeap[T](array, BinaryHeapOps.maxHeapUtils[T])
  }
}
