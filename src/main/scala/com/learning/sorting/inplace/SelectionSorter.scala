package com.learning.sorting.inplace

import com.learning.utils.Swapper

import scala.collection.mutable.ArrayBuffer

/**
  * - In place
  * - NOT Stable
  *   - due to the swaps with the ith element
  *   - imagine the array [4 2 4 1], in the first pass it will swap the 1
  *   with the first 4, making it appear after the second 4
  * - Time complexity:
  *   - Best: O(n^^2)
  *   - Average: O(n^^2)
  *   - Worst: O(n^^2)
  * - Space complexity: O(1)
  */
class SelectionSorter extends Sorter {
  override def sort[T](xs: ArrayBuffer[T])(implicit ev: Ordering[T]): Unit = {
    var min = 0
    for {i <- Range(0, xs.length - 1)} yield {
      min = i
      for {j <- Range(i + 1, xs.length)} yield {
        if (ev.lt(xs(j), xs(min))) {
          min = j
        }
      }

      if (min != i) {
        Swapper.swap(xs, i, min)
      }
    }
  }
}
