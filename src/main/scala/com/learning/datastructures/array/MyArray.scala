package com.learning.datastructures.array

import scala.reflect.ClassTag

class MyArray[T: ClassTag](initialCapacity: Int) {
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
  def removeLast(): T = {
    if (index == 0) {
      throw new IllegalStateException("Removing from empty array")
    }
    val element = array(index - 1)
    index = index - 1
    element
  }

  // O(n)
  def remove(i: Int): T = {
    if (index < i) {
      throw new ArrayIndexOutOfBoundsException(i)
    }
    val element = array(i)
    for (j <- i until index - 1) {
      array(j) = array(j + 1)
    }
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

  // O(n)
  override def equals(other: Any): Boolean = other match {
    case that: MyArray[T] => array.take(index).sameElements(that.array)
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
    s"DynamicArray(${array.take(index).mkString(", ")})"
  }
}

object MyArray {
  def apply[T: ClassTag](vs: T*): MyArray[T] = {
    val array = new MyArray[T](vs.size)
    vs.foreach(array.add)
    array
  }
}
