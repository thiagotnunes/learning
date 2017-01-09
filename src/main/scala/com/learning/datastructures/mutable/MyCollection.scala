package com.learning.datastructures.mutable

trait MyCollection[T] {
  def add(e: T): Unit

  def get(i: Int): Option[T]

  def size: Int

  def isEmpty: Boolean

  def reverse(): Unit

  def remove(i: Int): Option[T]
}
