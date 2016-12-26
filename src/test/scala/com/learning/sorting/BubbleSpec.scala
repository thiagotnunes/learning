package com.learning.sorting

import org.scalacheck.Prop.forAll
import org.specs2.ScalaCheck
import org.specs2.mutable.Specification

class BubbleSpec extends Specification with ScalaCheck {
  "sorts the array in ascending order" in {
    forAll { (xs: Array[Int]) =>
      Bubble.sort(xs)
      xs ==== xs.sorted
    }
  }

  "sorts input array in inverted order" in {
    val input = Array(10, 9, 8, 7, 6, 5, 4, 3, 2, 1)
    Bubble.sort(input)

    input ==== Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
  }
}
