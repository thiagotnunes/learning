package com.learning.sorting.immutable

import org.specs2.ScalaCheck
import org.specs2.mutable.Specification

trait SorterSpec extends Specification with ScalaCheck {
  val sorter: Sorter
}
