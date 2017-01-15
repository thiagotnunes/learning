package com.learning.datastructures.mutable.linkedlist

import com.learning.datastructures.mutable.linkedlist.LinkedList.Node

class LinkedList[T] {
  private var currentSize: Int = 0
  private var head: Node[T] = _

  // O(1) for insertions in the beginning of the list
  def add(e: T): Unit = {
    val newHead = Node(e, head)
    head = newHead
    currentSize = currentSize + 1
  }

  // O(1)
  def removeFirst(): Option[T] = {
    if (head == null) {
      None
    } else {
      val removed = head.value
      head = head.next
      currentSize = currentSize - 1

      Some(removed)
    }
  }

  // O(n)
  def remove(i: Int): Option[T] = {
    if (i >= size || head == null) {
      None
    } else if (i == 0) {
      removeFirst()
    } else {
      var p: Node[T] = head
      for (_ <- 0 until i - 1) {
        p = p.next
      }
      val toBeRemoved = p.next.value

      p.next = p.next.next
      currentSize = currentSize - 1

      Some(toBeRemoved)
    }
  }

  // O(n)
  def get(i: Int): Option[T] = {
    if (i >= size) {
      None
    } else {
      var e: Node[T] = head
      for (_ <- 0 until i) {
        e = e.next
      }
      Some(e.value)
    }
  }

  // O(1)
  def size: Int = {
    currentSize
  }

  // O(1)
  def isEmpty: Boolean = {
    size == 0
  }

  // O(n)
  def reverse(): Unit = {
    if (size != 0) {
      var previous: Node[T] = null
      var current: Node[T] = head
      var next: Node[T] = null

      do {
        next = current.next
        current.next = previous

        previous = current
        current = next
      } while (current != null)

      head = previous
    }
  }

  // O(n)
  override def equals(other: Any): Boolean = {
    other match {
      case otherList: LinkedList[T] if this.size == otherList.size =>
        var isEqual = true
        for (i <- 0 until size) {
          isEqual = isEqual && get(i) == otherList.get(i)
        }
        isEqual
      case _ => false
    }
  }

  // O(n)
  override def hashCode: Int = {
    var hashCode = 0
    for (i <- 0 until size) {
      hashCode = hashCode * 31 + get(i).hashCode()
    }
    hashCode
  }
}

object LinkedList {
  def apply[T](xs: T*): LinkedList[T] = {
    val list = new LinkedList[T]
    xs.reverse.foreach(list.add)
    list
  }

  def from[T](xs: Seq[T]): LinkedList[T] = {
    apply(xs: _*)
  }

  case class Node[T](value: T, var next: Node[T])

}
