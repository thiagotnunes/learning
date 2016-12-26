package com.learning.sorting.mutable

trait Sorter {
  def sort[T](xs: Array[T])(implicit o: Ordering[T]): Unit

  protected def swap[T](xs: Array[T], i: Int, j: Int): Unit = {
    val tmp = xs(j)
    xs(j) = xs(i)
    xs(i) = tmp
  }
}
