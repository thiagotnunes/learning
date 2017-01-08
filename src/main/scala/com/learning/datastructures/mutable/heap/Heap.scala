package com.learning.datastructures.mutable.heap

import com.learning.datastructures.mutable.array.DynamicArray

import scala.reflect.ClassTag


class Heap[T: ClassTag] private(array: DynamicArray[T], heapUtils: HeapUtils[T]) {
  private val heap: DynamicArray[T] = array

  // O(logn)
  def add(e: T): Unit = {
    heap.add(e)
    val i = size - 1

    heapUtils.siftUp(i, heap)
  }

  // O(1)
  def size: Int = {
    heap.size
  }

  // O(1)
  def peek: Option[T] = {
    heap.get(0)
  }

  // O(logn)
  def extract: Option[T] = {
    if (size > 0) {
      val e = heap.get(0)
      heap.swap(0, heap.size - 1)
      heap.removeLast()
      heapUtils.siftDown(0, heap)

      e
    } else {
      None
    }
  }

  // O(1)
  val asArray: DynamicArray[T] = heap

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

object Heap {
  def minHeap[T: ClassTag : Ordering](array: DynamicArray[T]): Heap[T] = {
    HeapUtils.minHeapUtils.heapify(array)
    new Heap[T](array, HeapUtils.minHeapUtils[T])
  }

  def maxHeap[T: ClassTag : Ordering](array: DynamicArray[T]): Heap[T] = {
    HeapUtils.maxHeapUtils.heapify(array)
    new Heap[T](array, HeapUtils.maxHeapUtils[T])
  }
}
