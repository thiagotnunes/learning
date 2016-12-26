package com.learning.sorting.mutable

import org.specs2.ScalaCheck
import org.specs2.mutable.Specification

trait SorterSpec extends Specification with ScalaCheck {
  val sorter: Sorter
}
