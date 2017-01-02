package com.learning.sorting.notinplace

import scala.reflect.ClassTag

trait Sorter {
  def sort[T: ClassTag](xs: Array[T])(implicit o: Ordering[T]): Array[T]
}
