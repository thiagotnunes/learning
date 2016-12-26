package com.learning.sorting.mutable

/**
  * - Stable
  * - Time complexity:
  *   - Best: O(n) when the array is already sorted
  *   - Average: O(n^^2)
  *   - Worst: O(n^^2)
  * - Space complexity: O(1)
  */
class BubbleSorter extends Sorter {
  override def sort[T: Ordering](xs: Array[T]): Unit = optimizedSort[T](xs)

  private def optimizedSort[T](xs: Array[T])(implicit o: Ordering[T]): Unit = {
    var hasSwapped = false
    var n = xs.length
    do {
      hasSwapped = false
      for {
        i <- Range(0, n - 1)
      } yield {
        if (o.gt(xs(i), xs(i + 1))) {
          swap(xs, i, i + 1)
          hasSwapped = true
        }
      }
      n = n - 1
    } while (hasSwapped)
  }

  private def simpleSort[T](xs: Array[T])(implicit o: Ordering[T]): Unit = {
    for {
      i <- Range(xs.length - 1, 0, -1)
      j <- Range(0, i)
    } yield {
      if (o.gt(xs(j), xs(j + 1))) {
        swap(xs, j, j + 1)
      }
    }
  }
}
