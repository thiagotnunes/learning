package com.learning.datastructures.mutable.tree.bst

import com.learning.datastructures.mutable.tree.Node

/**
  * This BST is not balanced:
  * - In the worst case the height h can be n
  * - In the best case the height h can be logn
  */
class BinarySearchTree[T](implicit ev: Ordering[T]) {
  private var rootNode: Option[Node[T]] = None
  private var currentSize: Int = 0

  // O(h)
  def add(e: T): Unit = {
    def go(node: Node[T]): Unit = {
      node match {
        case Node(ne, Some(left), _) if ev.lteq(e, ne) => go(left)
        case Node(ne, None, _) if ev.lteq(e, ne) => node.left = Some(Node.leaf(e))
        case Node(_, _, Some(right)) => go(right)
        case Node(_, _, None) => node.right = Some(Node.leaf(e))
      }
    }

    rootNode match {
      case None => rootNode = Some(Node.leaf(e))
      case Some(node) => go(node)
    }
    currentSize = currentSize + 1
  }

  // O(h)
  def find(e: T): Option[T] = {
    def go(node: Option[Node[T]]): Option[T] = {
      node match {
        case Some(Node(v, _, _)) if ev.equiv(e, v) => Some(v)
        case Some(Node(v, left, _)) if ev.lt(e, v) => go(left)
        case Some(Node(_, _, right)) => go(right)
        case None => None
      }
    }

    go(root)
  }

  // O(h)
  def remove(e: T): Boolean = {
    def go(node: Option[Node[T]]): Boolean = {
      node match {
        case Some(node@Node(_, Some(Node(v, left, right)), _)) if ev.equiv(e, v) =>
          (left, right) match {
            // Node to be removed is a leaf or has only one child
            case (_, None) =>
              node.left = left
              currentSize = currentSize - 1
            case (None, _) =>
              node.left = right
              currentSize = currentSize - 1
            // Node to be removed has two children
            case _ =>
              val maxElement = max(left).get
              remove(maxElement)
              node.left = Some(Node(maxElement, left, right))
          }
          true
        case Some(node@Node(_, _, Some(Node(v, left, right)))) if ev.equiv(e, v) =>
          (left, right) match {
            // Node to be removed is a leaf or has only one child
            case (_, None) =>
              node.right = left
              currentSize = currentSize - 1
            case (None, _) =>
              node.right = right
              currentSize = currentSize - 1
            // Node to be removed has two children
            case _ =>
              val maxElement = max(left).get
              remove(maxElement)
              node.right = Some(Node(maxElement, left, right))
          }
          true

        case Some(Node(v, left, _)) if ev.lteq(e, v) => go(left)
        case Some(Node(v, _, right)) if ev.gt(e, v) => go(right)
        case None => false
      }
    }

    if (rootNode.map(_.e).contains(e)) {
      rootNode = None
      currentSize = currentSize - 1
      true
    } else {
      go(root)
    }
  }

  // O(h)
  def max: Option[T] = {
    max(root)
  }

  def min: Option[T] = {
    min(root)
  }

  // O(h)
  private def max(node: Option[Node[T]]): Option[T] = {
    node match {
      case Some(Node(v, _, None)) => Some(v)
      case Some(Node(_, _, right)) => max(right)
      case None => None
    }
  }

  // O(h)
  private def min(node: Option[Node[T]]): Option[T] = {
    node match {
      case Some(Node(v, None, _)) => Some(v)
      case Some(Node(_, left, _)) => min(left)
      case None => None
    }
  }

  def root: Option[Node[T]] = {
    rootNode
  }

  // O(1)
  def size: Int = {
    currentSize
  }

  override def equals(other: Any): Boolean = {
    other match {
      case that: BinarySearchTree[T] => rootNode == that.rootNode
      case _ => false
    }
  }

  override def hashCode(): Int = {
    rootNode.hashCode()
  }
}

object BinarySearchTree {
  def from[T: Ordering](xs: Seq[T]): BinarySearchTree[T] = {
    val bst = new BinarySearchTree[T]()
    xs.foreach(bst.add)
    bst
  }
}
