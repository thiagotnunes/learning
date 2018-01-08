package com.learning.data_structures.mutable.tree.bst.avl

/**
  * This bst is balanced. It maintains the invariant that
  * for a given node, no left and right subtrees heights
  * may vary by more than 1.
  *
  * This means that the height, in the worst case, will be
  * O(logn).
  */
class AvlTree[T](rebalancer: Rebalancer[T])(implicit ev: Ordering[T]) {
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

      Some(rebalancer.rebalance(node))
    }

    rootNode match {
      case None => rootNode = Some(Node.leaf(e))
      case Some(node) => rootNode = go(node)
    }
    currentSize = currentSize + 1
  }

  // O(h)
  def remove(e: T): Boolean = {
    val (success, newRoot) = remove(rootNode, e)

    if (success) {
      rootNode = newRoot
      currentSize = currentSize - 1
    }

    success
  }

  // O(h)
  private def remove(node: Option[Node[T]], e: T): (Boolean, Option[Node[T]]) = {
    val (success, newNode) = node match {
      // match found :)
      case Some(Node(v, left, None, _)) if ev.equiv(e, v) => (true, left)
      case Some(Node(v, None, right, _)) if ev.equiv(e, v) => (true, right)
      case Some(n@Node(v, left, _, _)) if ev.equiv(e, v) =>
        val maxElement = max(left).get
        n.e = maxElement
        remove(left, maxElement)
        (true, node)

      // e < v, go left
      case Some(n@Node(v, left, _, _)) if ev.lt(e, v) =>
        val (success, l) = remove(left, e)
        n.left = l
        (success, node)

      // e > v, go right
      case Some(n@Node(v, _, right, _)) if ev.gt(e, v) =>
        val (success, r) = remove(right, e)
        n.right = r
        (success, node)

      // no match found :(
      case None => (false, None)
    }

    (success, newNode.map(rebalancer.rebalance))
  }


  // O(h)
  private def max(node: Option[Node[T]]): Option[T] = {
    node match {
      case Some(Node(v, _, None, _)) => Some(v)
      case Some(Node(_, _, right, _)) => max(right)
      case None => None
    }
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
}

object AvlTree {
  def getInstance[T: Ordering]: AvlTree[T] = {
    val heightCalculator = new HeightCalculator
    val rotator = new Rotator(heightCalculator)
    val rebalancer = new Rebalancer[T](rotator, heightCalculator)
    new AvlTree[T](rebalancer)
  }

  def from[T: Ordering](xs: Seq[T]): AvlTree[T] = {
    val tree = getInstance[T]
    xs.foreach(tree.add)
    tree
  }
}
