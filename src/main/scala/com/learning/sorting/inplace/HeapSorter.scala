package com.learning.sorting.inplace

import com.learning.data_structures.mutable.heap.BinaryHeapOps
import com.learning.utils.Swapper

import scala.collection.mutable.ArrayBuffer

/**
  * - In place
  * - NOT Stable
  *   - due to heap extraction
  *   - imagine the array [21 20a 20b 12 11 8 7], when extract max occurs we will
  *   get the following result [7 8 11 12 20b 20a 21]
  * - Time complexity:
  *   - Best: O(nlogn) when the array is already sorted
  *   - Average: O(nlogn)
  *   - Worst: O(nlogn)
  * - Space complexity: O(1)
  */
class HeapSorter extends Sorter {
  override def sort[T: Ordering](xs: ArrayBuffer[T]): Unit = {
    val heapUtils = BinaryHeapOps.maxHeapUtils

    heapUtils.heapify(xs)

    for (currentLength <- (xs.length - 1) to 0 by -1) {
      Swapper.swap(xs, 0, currentLength)
      heapUtils.siftDown(0, xs, currentLength)
    }
  }
}
