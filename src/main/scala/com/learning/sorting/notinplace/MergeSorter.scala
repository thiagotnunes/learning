package com.learning.sorting.notinplace

import scala.reflect.ClassTag

/**
  * - NOT In place
  * - Stable
  * - Time complexity:
  *   - Best: O(nlogn)
  *   - Average: O(nlogn)
  *   - Worst: O(nlogn)
  * - Space complexity: O(n)
  */
class MergeSorter extends Sorter {
  override def sort[T: ClassTag](xs: Array[T])(implicit o: Ordering[T]): Array[T] = {
    xs match {
      case Array() | Array(_) => xs
      case _ =>
        val (ys, zs) = xs.splitAt(xs.length / 2)
        merge(sort(ys), sort(zs))
    }
  }

  private def merge[T: ClassTag](xs: Array[T], ys: Array[T])(implicit o: Ordering[T]): Array[T] = {
    val mergedArray = Array.ofDim[T](xs.length + ys.length)
    var i = 0
    var j = 0
    for {k <- Range(0, xs.length + ys.length)} yield {
      if (j >= ys.length) {
        mergedArray(k) = xs(i)
        i = i + 1
      } else if (i >= xs.length) {
        mergedArray(k) = ys(j)
        j = j + 1
      } else if (o.lt(xs(i), ys(j))) {
        mergedArray(k) = xs(i)
        i = i + 1
      } else {
        mergedArray(k) = ys(j)
        j = j + 1
      }
    }
    mergedArray
  }
}
