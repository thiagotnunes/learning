package com.learning.sorting.inplace

import scala.util.Random

/**
  * - In place
  * - NOT Stable
  * - Time complexity:
  *   - Best: O(nlogn)
  *   - Average: O(nlogn)
  *   - Worst: O(n^^2)
  * - Space complexity: O(1)
  */
class QuickSorter extends Sorter {
  override def sort[T](xs: Array[T])(implicit o: Ordering[T]): Unit = {
    quickSort(xs, 0, xs.length - 1)
  }

  private def quickSort[T](xs: Array[T], lo: Int, hi: Int)(implicit o: Ordering[T]): Unit = {
    if (lo < hi) {
      val p = partition(xs, lo, hi)
      quickSort(xs, lo, p - 1)
      quickSort(xs, p + 1, hi)
    }
  }

  private def partition[T](xs: Array[T], lo: Int, hi: Int)(implicit o: Ordering[T]): Int = {
    val pivot = Random.nextInt(hi - lo) + lo
    swap(xs, hi, pivot)

    var i = lo
    for {k <- Range(lo, hi)} yield {
      if (o.lt(xs(k), xs(hi))) {
        swap(xs, k, i)
        i = i + 1
      }
    }

    swap(xs, i, hi)
    i
  }
}