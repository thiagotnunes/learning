package com.learning.data_structures.mutable.stack

import org.specs2.mutable.Specification

class StackSpec extends Specification {

  "push" >> {
    "inserts each element to the top of the stack" in {
      val stack = new Stack[Int](5)

      stack.push(1)
      stack.push(2)

      stack.toSeq ==== Seq(2, 1)
    }

    "throws an exception when max capacity is reached" in {
      val stack = new Stack[Int](1)

      stack.push(1)

      stack.push(2) must throwA[RuntimeException]
    }
  }

  "pop" >> {
    "removes element from the top of the stack" in {
      val stack = Stack.from(1, 2, 3)

      stack.pop() ==== 3
      stack.toSeq ==== Seq(2, 1)
    }

    "throws an exception when stack is empty" in {
      val stack = new Stack[Int](5)

      stack.pop() must throwA[RuntimeException]
    }
  }

  "size" >> {
    "returns 0 for empty stack" in {
      val stack = new Stack[Int](1)

      stack.size == 0
    }

    "returns number of elements in the stack" in {
      val stack = Stack.from(1, 2, 3)

      stack.size ==== 3
    }
  }

  "isEmpty" >> {
    "returns true when stack is empty" in {
      val stack = new Stack[Int](1)

      stack.isEmpty ==== true
    }

    "returns false when stack is not empty" in {
      val stack = Stack.from(1, 2, 3)

      stack.isEmpty ==== false
    }
  }

  "isFull" >> {
    "returns true when stack is full" in {
      val stack = Stack.from(1,2,3)

      stack.isFull ==== true
    }

    "returns false when stack is not full" in {
      val stack = new Stack[Int](2)

      stack.push(1)

      stack.isFull ==== false
    }
  }

  "peek" >> {
    "returns element in the top of the stack (but does not remove it)" in {
      val stack = Stack.from(1, 2, 3)

      stack.peek ==== 3
      stack.toSeq ==== Seq(3, 2, 1)
    }

    "throws an exception when the stack is empty" in {
      val stack = new Stack[Int](5)

      stack.peek must throwA[RuntimeException]
    }
  }

  "maintains stack state after max capacity has been reached" in {
    val stack = new Stack[Int](3)

    stack.push(1)
    stack.push(2)
    stack.push(3)
    stack.pop()
    stack.push(4)
    stack.pop()
    stack.pop()
    stack.push(5)
    stack.push(6)

    stack.peek ==== 6
    stack.toSeq ==== Seq(6, 5, 1)
    stack.size ==== 3
  }
}
