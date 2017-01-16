package com.learning.sorting.inplace

import com.learning.utils.Swapper

import scala.collection.mutable.ArrayBuffer
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
  override def sort[T: Ordering](xs: ArrayBuffer[T]): Unit = {
    quickSort(xs, 0, xs.length - 1)
  }

  private def quickSort[T: Ordering](xs: ArrayBuffer[T], lo: Int, hi: Int): Unit = {
    if (lo < hi) {
      val p = partition(xs, lo, hi)
      quickSort(xs, lo, p - 1)
      quickSort(xs, p + 1, hi)
    }
  }

  private def partition[T](xs: ArrayBuffer[T], lo: Int, hi: Int)(implicit ev: Ordering[T]): Int = {
    val pivot = Random.nextInt(hi - lo) + lo
    Swapper.swap(xs, hi, pivot)

    var i = lo
    for {k <- Range(lo, hi)} yield {
      if (ev.lt(xs(k), xs(hi))) {
        Swapper.swap(xs, k, i)
        i = i + 1
      }
    }

    Swapper.swap(xs, i, hi)
    i
  }
}
