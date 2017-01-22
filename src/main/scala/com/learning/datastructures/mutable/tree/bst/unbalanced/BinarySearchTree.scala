package com.learning.datastructures.mutable.tree.bst.unbalanced

import com.learning.utils.Bounded

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
    val removalSuccessful = rootNode match {
      case Some(Node(v, left, None)) if ev.equiv(e, v) =>
        rootNode = left
        true
      case Some(Node(v, _, right)) if ev.equiv(e, v) =>
        rootNode = right
        true
      case _ => remove(rootNode, null, e)
    }

    if (removalSuccessful) {
      currentSize = currentSize - 1
    }

    removalSuccessful
  }

  // O(h)
  private def remove(node: Option[Node[T]], parent: Node[T], e: T): Boolean = {
    node match {
      case Some(n@Node(v, left, right)) if ev.equiv(e, v) =>
        (left, right, parent.left == node) match {
          case (l, None, true) => parent.left = l
          case (l, None, false) => parent.right = l
          case (None, r, true) => parent.left = r
          case (None, r, false) => parent.right = r
          case _ =>
            val maxElement = max(left).get
            n.e = maxElement
            remove(left, n, maxElement)
        }
        true
      case Some(n@Node(v, left, _)) if ev.lteq(e, v) => remove(left, n, e)
      case Some(n@Node(v, _, right)) if ev.gteq(e, v) => remove(right, n, e)
      case None => false
    }
  }

  // O(h)
  def max: Option[T] = {
    max(root)
  }

  // O(h)
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

  def isValid[T](root: Option[Node[T]])(implicit ev: Ordering[T], bounds: Bounded[T]): Boolean = {
    def go(root: Option[Node[T]], range: (T, T)): Boolean = {
      root match {
        case Some(Node(v, left, right)) =>
          (ev.gt(v, range._1) && ev.lteq(v, range._2)) &&
            go(left, (range._1, v)) &&
            go(right, (v, range._2))
        case None => true
      }
    }

    go(root, (bounds.min, bounds.max))
  }
}
