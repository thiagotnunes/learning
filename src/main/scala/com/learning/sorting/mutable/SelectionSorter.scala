package com.learning.sorting.mutable

/**
  * - NOT Stable
  * - Time complexity: O(n^^2)
  * - Space complexity: O(1)
  */
class SelectionSorter extends Sorter {
  override def sort[T](xs: Array[T])(implicit o: Ordering[T]): Unit = {
    var min = 0
    for { i <- Range(0, xs.length - 1) } yield {
      min = i
      for { j <- Range(i + 1, xs.length) } yield {
        if (o.lt(xs(j), xs(min))) {
          min = j
        }
      }

      if (min != i) {
        swap(xs, i, min)
      }
    }
  }
}
