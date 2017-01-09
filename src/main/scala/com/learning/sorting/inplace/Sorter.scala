package com.learning.sorting.inplace

import scala.reflect.ClassTag

trait Sorter {
  def sort[T: ClassTag : Ordering](xs: Array[T]): Unit

  protected def swap[T](xs: Array[T], i: Int, j: Int): Unit = {
    val tmp = xs(j)
    xs(j) = xs(i)
    xs(i) = tmp
  }
}
