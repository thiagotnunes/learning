package com.learning.datastructures.mutable.tree.segment

/**
  * Space complexity - O(4n)
  *
  * Build - O(n)
  * Query - O(logn)
  * Update - O(logn)
  */
class SegmentTree(array: Array[Int]) {
  private val treeSize = treeSizeFrom(array.length)
  private val tree = Array.fill(treeSize)(Integer.MAX_VALUE)
  private val maxRange = array.length - 1
  build(0, maxRange, 0)

  def getTree: Array[Int] = {
    tree.filter(_ != Integer.MAX_VALUE)
  }

  def query(from: Int, to: Int): Int = {
    query(from, to, 0, maxRange, 0)
  }

  def update(i: Int, e: Int): Unit = {
    if (i > maxRange) {
      throw new ArrayIndexOutOfBoundsException(i)
    }

    array(i) = e
    update(0, maxRange, 0, i, e)
  }

  // O(logn)
  private def update(from: Int, to: Int, treeI: Int, i: Int, newElement: Int): Unit = {
    if (i == from && i == to) {
      tree(treeI) = array(i)
    } else if (i >= from && i <= to) {
      val mid = (from + to) / 2
      val leftIndex = 2 * treeI + 1
      val rightIndex = 2 * treeI + 2

      update(from, mid, leftIndex, i, newElement)
      update(mid + 1, to, rightIndex, i, newElement)

      tree(treeI) = Math.min(tree(leftIndex), tree(rightIndex))
    }
  }

  // O(logn)
  private def query(from: Int, to: Int, left: Int, right: Int, i: Int): Int = {
    if (left >= from && right <= to) {
      tree(i)
    } else if (right < from || left > to) {
      Integer.MAX_VALUE
    } else {
      val mid = (left + right) / 2
      Math.min(
        query(from, to, left, mid, 2 * i + 1),
        query(from, to, mid + 1, right, 2 * i + 2)
      )
    }
  }

  // O(n)
  private def build(from: Int, to: Int, i: Int): Unit = {
    if (from == to) {
      tree(i) = array(from)
    } else if (from < to) {
      val mid = (from + to) / 2
      val leftIndex = 2 * i + 1
      val rightIndex = 2 * i + 2

      build(from, mid, leftIndex)
      build(mid + 1, to, rightIndex)

      tree(i) = Math.min(tree(leftIndex), tree(rightIndex))
    }
  }

  private def treeSizeFrom(length: Int): Int = {
    val h = math.ceil(math.log(length) / math.log(2)).toInt
    2 * math.pow(2, h).toInt - 1
  }
}
