package com.learning.sorting

/**
  * Bubble sort
  *  - Mutative implementation
  *  - Stable
  *  - Time complexity: O(n^^2)
  *  - Space complexity: O(1)
  */
object Bubble {
  def sort[T](xs: Array[T])(implicit o: Ordering[T]): Unit = {
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

  def sort2[T](xs: Array[T])(implicit o: Ordering[T]): Unit = {
    for {
      i <- Range(xs.length - 1, 0, -1)
      j <- Range(0, i)
    } yield {
      if (o.gt(xs(j), xs(j + 1))) {
        swap(xs, j, j + 1)
      }
    }
  }

  private def swap[T](xs: Array[T], i: Int, j: Int): Unit = {
    val tmp = xs(j)
    xs(j) = xs(i)
    xs(i) = tmp
  }
}
