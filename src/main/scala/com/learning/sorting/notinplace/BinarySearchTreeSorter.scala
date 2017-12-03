package com.learning.sorting.notinplace

import com.learning.datastructures.mutable.tree.bst.avl.{AvlTree, Node}
import com.learning.algorithms.traversal.DepthFirst

import scala.reflect.ClassTag

/**
  * - NOT In place
  * - NOT Stable
  * - Time complexity:
  *   - Best: O(nlogn)
  *   - Average: O(nlogn)
  *   - Worst: O(nlogn)
  * - Space complexity: O(n)
  */
class BinarySearchTreeSorter extends Sorter {
  private val traverser = new DepthFirst

  override def sort[T: ClassTag](xs: Array[T])(implicit o: Ordering[T]): Array[T] = {
    val tree = AvlTree.getInstance[T]
    xs.foreach(tree.add)
    tree.root match {
      case Some(root) => traverser.traverse(root)(DepthFirst.inOrder[T, Node]).toArray
      case None => Array.empty[T]
    }
  }
}
