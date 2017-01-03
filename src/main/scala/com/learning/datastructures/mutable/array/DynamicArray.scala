package com.learning.datastructures.mutable.array

import com.learning.datastructures.mutable.MyCollection

import scala.reflect.ClassTag

class DynamicArray[T: ClassTag](initialCapacity: Int) extends MyCollection[T] {
  private var currentCapacity = initialCapacity
  private var array = Array.ofDim[T](currentCapacity)
  private var index = 0

  // O(1) on average
  def add(e: T): Unit = {
    if (index == currentCapacity) {
      currentCapacity = currentCapacity * 2
      val newArray = Array.ofDim[T](currentCapacity)
      Array.copy(array, 0, newArray, 0, size)
      array = newArray
    }
    array(index) = e
    index = index + 1
  }

  // O(1)
  def removeLast(): T = {
    if (index == 0) {
      throw new ArrayIndexOutOfBoundsException(0)
    }
    remove(index - 1)
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

  def capacity: Int = {
    currentCapacity
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
    case that: DynamicArray[T] => array.take(index).sameElements(that.array)
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

object DynamicArray {
  def apply[T: ClassTag](vs: T*): DynamicArray[T] = {
    val array = new DynamicArray[T](vs.size)
    vs.foreach(array.add)
    array
  }

  def from[T: ClassTag](vs: Seq[T]): DynamicArray[T] = {
    apply(vs: _*)
  }
}
