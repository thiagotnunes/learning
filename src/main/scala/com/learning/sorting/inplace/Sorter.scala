package com.learning.sorting.inplace

import scala.collection.mutable.ArrayBuffer

trait Sorter {
  def sort[T: Ordering](xs: ArrayBuffer[T]): Unit
}
