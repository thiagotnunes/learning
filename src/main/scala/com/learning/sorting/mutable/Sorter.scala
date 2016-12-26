package com.learning.sorting.mutable

import scala.reflect.ClassTag

trait Sorter {
  def sort[T : ClassTag](xs: Array[T])(implicit o: Ordering[T]): Unit

  protected def swap[T](xs: Array[T], i: Int, j: Int): Unit = {
    val tmp = xs(j)
    xs(j) = xs(i)
    xs(i) = tmp
  }
}
