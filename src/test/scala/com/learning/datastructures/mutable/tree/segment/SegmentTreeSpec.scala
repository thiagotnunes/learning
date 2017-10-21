package com.learning.datastructures.mutable.tree.segment

import org.specs2.mutable.Specification

class SegmentTreeSpec extends Specification {

  "build" >> {
    "builds min segment tree from given array with power of 2 size" in {
      new SegmentTree(Array(1, 2, 3, 4)).getTree ==== Array(1, 1, 3, 1, 2, 3, 4)
    }

    "build min segment tree from given array with non power of 2 size" in {
      new SegmentTree(Array(1, 2, 3, 4, 5)).getTree ==== Array(1, 1, 4, 1, 3, 4, 5, 1, 2)
    }
  }

  "query" >> {
    "returns the min element in the given range" in {
      new SegmentTree(Array(1, 2, 3, 4, 5)).query(1, 3) ==== 2
    }

    "returns the element at the position when from and to are the same" in {
      new SegmentTree(Array(1, 2, 3, 4, 5)).query(2, 2) ==== 3
    }

    "returns max value when given range is to big" in {
      new SegmentTree(Array(1, 2, 3, 4, 5)).query(5, 6) ==== Integer.MAX_VALUE
    }

    "returns max value when given range is too small" in {
      new SegmentTree(Array(1, 2, 3, 4, 5)).query(-2, -1) ==== Integer.MAX_VALUE
    }
  }

  "update" >> {
    "updates the element value and recalculates the min" in {
      val tree = new SegmentTree(Array(1, 2, 3, 4, 5))
      tree.query(1, 3) ==== 2

      tree.update(2, 0)

      tree.query(1, 3) ==== 0
    }

    "only propagates updated value while it is the min" in {
      val tree = new SegmentTree(Array(1, 2, 3, 4, 5))
      tree.query(0, 4) === 1

      tree.update(1, 10)

      tree.query(1, 1) ==== 10
      tree.query(1, 2) ==== 3
      tree.query(0, 4) ==== 1
    }

    "returns error if value is out of tree the range" in {
      new SegmentTree(Array(1, 2, 3, 4, 5)).update(5, 10) must throwA[ArrayIndexOutOfBoundsException]
    }
  }
}
