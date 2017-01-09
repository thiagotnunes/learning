package com.learning.sorting.inplace

import scala.reflect.ClassTag

/**
  * - In place
  * - NOT Stable
  * - Time complexity:
  *   - Best: O(n^^2)
  *   - Average: O(n^^2)
  *   - Worst: O(n^^2)
  * - Space complexity: O(1)
  */
class SelectionSorter extends Sorter {
  override def sort[T: ClassTag](xs: Array[T])(implicit ev: Ordering[T]): Unit = {
    var min = 0
    for { i <- Range(0, xs.length - 1) } yield {
      min = i
      for { j <- Range(i + 1, xs.length) } yield {
        if (ev.lt(xs(j), xs(min))) {
          min = j
        }
      }

      if (min != i) {
        swap(xs, i, min)
      }
    }
  }
}
