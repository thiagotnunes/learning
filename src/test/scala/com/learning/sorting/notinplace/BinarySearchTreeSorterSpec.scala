package com.learning.sorting.notinplace

import com.learning.PropertySpecification
import org.scalacheck.Prop.forAll

class BinarySearchTreeSorterSpec extends PropertySpecification {
  val sorter: Sorter = new BinarySearchTreeSorter

  "sorts the array in ascending order" in {
    forAll { (xs: Array[Int]) =>
      sorter.sort(xs) ==== xs.sorted
    }
  }

  "sorts the inverted order array" in {
    sorter.sort(
      Array(10, 9, 8, 7, 6, 5, 4, 3, 2, 1)
    ) ==== Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
  }
}
