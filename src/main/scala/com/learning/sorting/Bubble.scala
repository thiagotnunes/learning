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
