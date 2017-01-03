package com.learning.datastructures.linkedlist

import com.learning.datastructures.linkedlist.SinglyLinkedList.Node

class SinglyLinkedList[T] {
  private var currentSize: Int = 0
  private var head: Node[T] = _

  // O(1) for insertions in the beginning of the list
  def add(e: T): SinglyLinkedList[T] = {
    val newHead = Node(e, head)
    head = newHead
    currentSize = currentSize + 1
    this
  }

  // O(1)
  def removeFirst(): T = {
    if (head == null) {
      throw new IndexOutOfBoundsException("Can not remove from an empty list")
    }

    val removed = head.value
    head = head.next
    currentSize = currentSize - 1

    removed
  }

  def remove(i: Int): T = {
    if (i > size || head == null) {
      throw new IndexOutOfBoundsException(s"Can not remove $i element from list with size $size")
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

      toBeRemoved
    }
  }

  // O(n)
  def get(i: Int): T = {
    var e: Node[T] = head
    for (_ <- 0 until i) {
      e = e.next
    }
    e.value
  }

  // O(1)
  def size: Int = {
    currentSize
  }

  // O(n)
  override def equals(other: Any): Boolean = {
    other match {
      case otherList: SinglyLinkedList[T] if this.currentSize == otherList.currentSize =>
        var isEqual = true
        for (i <- 0 until currentSize) {
          isEqual = isEqual && get(i) == otherList.get(i)
        }
        isEqual
      case _ => false
    }
  }

  // O(n)
  override def hashCode: Int = {
    var hashCode = 0
    for (i <- 0 until currentSize) {
      hashCode = hashCode * 31 + get(i).hashCode()
    }
    hashCode
  }
}

object SinglyLinkedList {
  def apply[T](xs: T*): SinglyLinkedList[T] = {
    val list = new SinglyLinkedList[T]
    xs.reverse.foreach(list.add)
    list
  }

  def from[T](xs: Seq[T]): SinglyLinkedList[T] = {
    apply(xs: _*)
  }

  case class Node[T](value: T, var next: Node[T])

}
