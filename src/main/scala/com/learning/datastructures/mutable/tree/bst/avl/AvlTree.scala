package com.learning.datastructures.mutable.tree.bst.avl

/**
  * This bst is balanced. It maintains the invariant that
  * for a given node, no left and right subtrees heights
  * may vary by more than 1.
  *
  * This means that the height, in the worst case, will be
  * O(logn).
  */
class AvlTree[T](rotator: Rotator,
                 heightCalculator: HeightCalculator)
                (implicit ev: Ordering[T]) {
  private var rootNode: Option[Node[T]] = None
  private var currentSize: Int = 0

  // O(h)
  def add(e: T): Unit = {
    def go(node: Node[T]): Option[Node[T]] = {
      node match {
        case Node(v, Some(left), _, _) if ev.lteq(e, v) => node.left = go(left)
        case Node(v, None, _, _) if ev.lteq(e, v) => node.left = Some(Node.leaf(e))
        case Node(_, _, Some(right), _) => node.right = go(right)
        case Node(_, _, None, _) => node.right = Some(Node.leaf(e))
      }

      val newNode = nodeBalance(node) match {
        case LeftLeft =>
          Option(rotator.rotateRight(node))
        case LeftRight =>
          node.right = Some(rotator.rotateRight(node.right.get))
          Option(rotator.rotateLeft(node))
        case RightLeft =>
          node.left = Some(rotator.rotateLeft(node.left.get))
          Option(rotator.rotateRight(node))
        case RightRight =>
          Option(rotator.rotateLeft(node))
        case Balanced =>
          Some(node)
      }

      node.height = heightCalculator.fromSubtrees(node)
      newNode
    }

    rootNode match {
      case None => rootNode = Some(Node.leaf(e))
      case Some(node) => rootNode = go(node)
    }
    currentSize = currentSize + 1
  }

  // O(h)
  def find(e: T): Option[T] = {
    def go(node: Option[Node[T]]): Option[T] = {
      node match {
        case Some(Node(v, _, _, _)) if ev.equiv(e, v) => Some(v)
        case Some(Node(v, left, _, _)) if ev.lt(e, v) => go(left)
        case Some(Node(_, _, right, _)) => go(right)
        case None => None
      }
    }

    go(root)
  }

  // O(1)
  def size: Int = {
    currentSize
  }

  // O(1)
  def root: Option[Node[T]] = {
    rootNode
  }

  private def nodeBalance(node: Node[T]): NodeBalance = {
    val balance = heightCalculator.fromNode(node.left) - heightCalculator.fromNode(node.right)
    if (balance > 1) {
      if (heightCalculator.fromNode(node.left.flatMap(_.left)) >= heightCalculator.fromNode(node.left.flatMap(_.right))) {
        LeftLeft
      } else {
        RightLeft
      }
    } else if (balance < -1) {
      if (heightCalculator.fromNode(node.right.flatMap(_.left)) >= heightCalculator.fromNode(node.right.flatMap(_.right))) {
        LeftRight
      } else {
        RightRight
      }
    } else {
      Balanced
    }
  }
}

object AvlTree {
  def getInstance[T: Ordering]: AvlTree[T] = {
    val heightCalculator = new HeightCalculator
    val rotator = new Rotator(heightCalculator)
    new AvlTree[T](rotator, heightCalculator)
  }

  def from[T: Ordering](xs: Seq[T]): AvlTree[T] = {
    val tree = getInstance[T]
    xs.foreach(tree.add)
    tree
  }
}
