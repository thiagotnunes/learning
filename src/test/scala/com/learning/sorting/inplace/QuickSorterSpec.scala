package com.learning.sorting.inplace

import com.learning.PropertySpecification
import org.scalacheck.Prop.forAll

class QuickSorterSpec extends PropertySpecification {
  val sorter: Sorter = new QuickSorter

  "sorts the array in ascending order" in {
    forAll { (xs: Array[Int]) =>
      sorter.sort(xs)
      xs ==== xs.sorted
    }
  }

  "sorts the inverted order array" in {
    val input = Array(10, 9, 8, 7, 6, 5, 4, 3, 2, 1)
    sorter.sort(input)

    input ==== Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
  }
}
