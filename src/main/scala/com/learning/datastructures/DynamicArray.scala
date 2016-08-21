package com.learning.datastructures

import scala.reflect.ClassTag

class DynamicArray[T: ClassTag](initialCapacity: Int) {
  private var capacity = initialCapacity
  private var array = Array.ofDim[T](capacity)
  private var index = 0

  // O(1) on average
  def add(e: T): Unit = {
    if (index == capacity) {
      capacity = capacity * 2
      val newArray = Array.ofDim[T](capacity)
      Array.copy(array, 0, newArray, 0, size)
      array = newArray
    }
    array(index) = e
    index = index + 1
  }

  // O(1)
  def remove(): T = {
    if (index == 0) {
      throw new IllegalStateException("Removing from empty array")
    }
    val element = array(index - 1)
    index = index - 1
    element
  }

  // O(1)
  def get(i: Int): T = {
    if (i < index) {
      array(i)
    } else {
      throw new ArrayIndexOutOfBoundsException(i)
    }
  }

  // O(1)
  def size: Int = {
    index
  }

  def currentCapacity: Int = {
    capacity
  }

  // O(n)
  def reverse(): Unit = {
    for (i <- 0 until (size / 2)) {
      val tmp = array(i)
      array(i) = array(size - 1 - i)
      array(size - 1 - i) = tmp
    }
  }

  override def equals(other: Any): Boolean = other match {
    case that: DynamicArray[T] => array.sameElements(that.array)
    case _ => false
  }

  // O(n)
  override def hashCode(): Int = {
    var hashCode = 0
    for (i <- 0 until size) {
      hashCode = hashCode * 31 + get(i).hashCode()
    }
    hashCode
  }

  override def toString: String = {
    s"DynamicArray(${array.mkString(", ")})"
  }
}

object DynamicArray {
  def apply[T: ClassTag](vs: T*): DynamicArray[T] = {
    val array = new DynamicArray[T](vs.size)
    vs.foreach(array.add)
    array
  }
}
