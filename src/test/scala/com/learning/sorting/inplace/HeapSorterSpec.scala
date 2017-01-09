package com.learning.sorting.inplace

import com.learning.PropertySpecification
import org.scalacheck.Prop.forAll

import scala.collection.mutable.ArrayBuffer

class HeapSorterSpec extends PropertySpecification {
  val sorter: Sorter = new HeapSorter

  "sorts the array in ascending order" in {
    forAll { (xs: ArrayBuffer[Int]) =>
      sorter.sort(xs)
      xs ==== xs.sorted
    }
  }

  "sorts the inverted order array" in {
    val input = ArrayBuffer(10, 9, 8, 7, 6, 5, 4, 3, 2, 1)
    sorter.sort(input)

    input ==== ArrayBuffer(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
  }
}
