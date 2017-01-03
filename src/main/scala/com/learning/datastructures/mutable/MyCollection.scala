package com.learning.datastructures.mutable

trait MyCollection[T] {
  def add(e: T): Unit
  def get(i: Int): T
  def size: Int
  def reverse(): Unit
  def remove(i: Int): T
}
