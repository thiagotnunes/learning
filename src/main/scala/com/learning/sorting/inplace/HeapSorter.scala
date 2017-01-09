package com.learning.sorting.inplace

import com.learning.Swapper
import com.learning.datastructures.mutable.heap.HeapUtils

import scala.collection.mutable.ArrayBuffer

/**
  * - In place
  * - NOT Stable
  * - Time complexity:
  *   - Best: O(nlogn) when the array is already sorted
  *   - Average: O(nlogn)
  *   - Worst: O(nlogn)
  * - Space complexity: O(1)
  */
class HeapSorter extends Sorter {
  override def sort[T: Ordering](xs: ArrayBuffer[T]): Unit = {
    val heapUtils = HeapUtils.maxHeapUtils

    heapUtils.heapify(xs)

    for (currentLength <- (xs.length - 1) to 0 by -1) {
      Swapper.swap(xs, 0, currentLength)
      heapUtils.siftDown(0, xs, currentLength)
    }
  }
}
