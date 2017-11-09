package com.learning.datastructures.mutable.queue

import org.specs2.mutable.Specification

class PriorityQueueSpec extends Specification {

  "enqueue" >> {
    "maintains min heap invariant" in {
      val queue = new PriorityQueue[Int]()

      queue.enqueue(1, 50)
      queue.enqueue(2, 40)
      queue.enqueue(3, 30)
      queue.enqueue(4, 31)

      queue.toSeq ==== Seq(3, 4, 2, 1)
    }

    "throws an exception when trying to add an existing value" in {
      val queue = new PriorityQueue[Int]()

      queue.enqueue(1, 40)

      queue.enqueue(1, 50) must throwA[ValueExistsException[Int]]
    }
  }

  "dequeue" >> {
    "removes the first item from the queue" in {
      val queue = new PriorityQueue[Int]()

      queue.enqueue(1, 50)
      queue.enqueue(2, 40)
      queue.enqueue(3, 30)
      queue.enqueue(4, 31)

      queue.dequeue() ==== 3
      queue.size ==== 3
    }

    "throws an exception when trying to dequeue from empty queue" in {
      val queue = new PriorityQueue[Int]()

      queue.dequeue() must throwA[EmptyQueueException.type]
    }
  }

  "decreasePriority" >> {
    "maintains min heap invariant" in {
      val queue = new PriorityQueue[Int]()

      queue.enqueue(1, 50)
      queue.enqueue(2, 40)
      queue.enqueue(3, 30)
      queue.enqueue(4, 31)

      queue.decreasePriority(1, 1)
      queue.toSeq ==== Seq(1, 3, 2, 4)
    }

    "throws an exception when trying to decrease priority of non-existing value" in {
      val queue = new PriorityQueue[Int]()

      queue.decreasePriority(10, 1) must throwA[ValueNotFoundException[Int]]
    }
  }

}
