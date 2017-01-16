package com.learning.utils

trait Bounded[T] {
  def min: T

  def max: T
}

object Bounded {
  implicit val intBounded: Bounded[Int] = new Bounded[Int] {
    override def min: Int = Int.MinValue

    override def max: Int = Int.MaxValue
  }
}
