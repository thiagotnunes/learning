package com.learning.sorting.mutable

/**
  * - In place
  * - Stable
  * - Time complexity:
  *   - Best: O(n) when the input array is already sorted
  *   - Average: O(n^^2)
  *   - Worst: O(n^^2)
  * - Space complexity: O(1)
  */
class InsertionSorter extends Sorter {
  override def sort[T](xs: Array[T])(implicit o: Ordering[T]): Unit = {
    for {i <- Range(1, xs.length)} yield {
      var j = i
      while (j > 0 && o.lt(xs(j), xs(j - 1))) {
        swap(xs, j, j - 1)
        j = j - 1
      }
    }
  }
}
