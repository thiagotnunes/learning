package com.learning

import scala.collection.mutable.ArrayBuffer

object Swapper {
  // O(1)
  def swap[T](array: ArrayBuffer[T], i: Int, j: Int): Unit = {
    val length = array.length
    if (i < length && j < length) {
      val tmp = array(i)
      array(i) = array(j)
      array(j) = tmp
    } else if (i >= length) {
      throw new ArrayIndexOutOfBoundsException(i)
    } else {
      throw new ArrayIndexOutOfBoundsException(j)
    }
  }
}
