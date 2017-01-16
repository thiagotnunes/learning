package com.learning.datastructures.mutable.queue

import org.specs2.mutable.Specification

class QueueSpec extends Specification {

  "enqueue" >> {
    "inserts each element to the end of the queue" in {
      val queue = new Queue[Int](5)

      queue.enqueue(1)
      queue.enqueue(2)

      queue.toSeq ==== Seq(1, 2)
    }

    "throws an exception when max capacity is reached" in {
      val queue = new Queue[Int](1)

      queue.enqueue(1)

      queue.enqueue(2) must throwA[RuntimeException]
    }
  }

  "dequeue" >> {
    "removes element from the front of the queue" in {
      val queue = Queue.from(1, 2, 3)

      queue.dequeue ==== 1
      queue.toSeq ==== Seq(2, 3)
    }

    "throws an exception when queue is empty" in {
      val queue = new Queue[Int](5)

      queue.dequeue must throwA[RuntimeException]
    }
  }

  "size" >> {
    "returns 0 for empty queue" in {
      val queue = new Queue[Int](1)

      queue.size == 0
    }

    "returns number of elements in the queue" in {
      val queue = Queue.from(1, 2, 3)

      queue.size ==== 3
    }
  }

  "isEmpty" >> {
    "returns true when queue is empty" in {
      val queue = new Queue[Int](1)

      queue.isEmpty ==== true
    }

    "returns false when queue is not empty" in {
      val queue = Queue.from(1, 2, 3)

      queue.isEmpty ==== false
    }
  }

  "isFull" >> {
    "returns true when queue is full" in {
      val queue = Queue.from(1,2,3)

      queue.isFull ==== true
    }

    "returns false when queue is not full" in {
      val queue = new Queue[Int](2)

      queue.enqueue(1)

      queue.isFull ==== false
    }
  }

  "peek" >> {
    "returns element in the front of the queue (but does not remove it)" in {
      val queue = Queue.from(1, 2, 3)

      queue.peek ==== 1
      queue.toSeq ==== Seq(1, 2, 3)
    }

    "throws an exception when the queue is empty" in {
      val queue = new Queue[Int](5)

      queue.peek must throwA[RuntimeException]
    }
  }

  "maintains queue state after max capacity has been reached" in {
    val queue = new Queue[Int](3)

    queue.enqueue(1)
    queue.enqueue(2)
    queue.enqueue(3)
    queue.dequeue
    queue.enqueue(4)
    queue.dequeue
    queue.dequeue
    queue.enqueue(5)
    queue.enqueue(6)

    queue.peek ==== 4
    queue.toSeq ==== Seq(4, 5, 6)
    queue.size ==== 3
  }

  "converts to sequence even when start > end" in {
    val queue = new Queue[Int](3)

    queue.enqueue(1)
    queue.enqueue(2)
    queue.dequeue
    queue.dequeue
    queue.enqueue(3)
    queue.enqueue(4)

    queue.peek ==== 3
    queue.toSeq ==== Seq(3, 4)
    queue.size ==== 2
  }
}
