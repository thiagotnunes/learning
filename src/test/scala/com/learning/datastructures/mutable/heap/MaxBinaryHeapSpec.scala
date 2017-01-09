package com.learning.datastructures.mutable.heap

import com.learning.PropertySpecification
import org.scalacheck.Prop.forAll
import org.scalacheck.{Arbitrary, Gen}

import scala.collection.mutable.ArrayBuffer

class MaxBinaryHeapSpec extends PropertySpecification {

  "heap creation" >> {
    "does nothing when array is already a max heap" in {
      BinaryHeap.maxHeap(ArrayBuffer(7, 6, 5, 4, 3, 2, 1)).asArray ==== ArrayBuffer(7, 6, 5, 4, 3, 2, 1)
    }

    "rearranges array when the input given is not a max heap" in {
      BinaryHeap.maxHeap(ArrayBuffer(1, 2, 3, 4, 5, 6, 7)).asArray ==== ArrayBuffer(7, 5, 6, 4, 2, 1, 3)
    }
  }

  "max" >> {
    "returns the max element" in {
      forAll(Gen.nonEmptyListOf(Arbitrary.arbitrary[Int])) { (xs: Seq[Int]) =>
        BinaryHeap.maxHeap(ArrayBuffer(xs: _*)).peek ==== Option(xs.max)
      }
    }

    "returns None when heap is empty" in {
      BinaryHeap.maxHeap(ArrayBuffer()).peek ==== None
    }
  }

  "extract max" >> {
    "returns the maximum element" in {
      forAll(Gen.nonEmptyListOf(Arbitrary.arbitrary[Int])) { (xs: Seq[Int]) =>
        BinaryHeap.maxHeap(ArrayBuffer(xs: _*)).extract ==== Option(xs.max)
      }

      "rearranges array to be a max heap" in {
        val heap = BinaryHeap.maxHeap(ArrayBuffer(1, 2, 3, 4, 5, 6, 7))

        heap.extract

        heap.asArray ==== ArrayBuffer(6, 5, 3, 4, 2, 1)
      }
    }

    "returns None when the heap is empty" in {
      BinaryHeap.maxHeap(ArrayBuffer[Int]()).extract ==== None
    }
  }

  "add" >> {
    "maintains max heap invariant" in {
      val heap = BinaryHeap.maxHeap(ArrayBuffer.empty[Int])

      heap.add(10)
      heap.asArray ==== ArrayBuffer(10)

      heap.add(20)
      heap.asArray ==== ArrayBuffer(20, 10)

      heap.add(30)
      heap.asArray ==== ArrayBuffer(30, 10, 20)

      heap.add(15)
      heap.asArray ==== ArrayBuffer(30, 15, 20, 10)

      heap.add(40)
      heap.asArray ==== ArrayBuffer(40, 30, 20, 10, 15)
    }
  }
}
