package com.learning.datastructures.mutable.heap

import com.learning.PropertySpecification
import org.scalacheck.Prop.forAll
import org.scalacheck.{Arbitrary, Gen}

import scala.collection.mutable.ArrayBuffer

class MinBinaryHeapSpec extends PropertySpecification {

  "heap creation" >> {
    "does nothing when array is already a min heap" in {
      BinaryHeap.minHeap(ArrayBuffer(1, 2, 3, 4, 5, 6, 7)).asArray ==== ArrayBuffer(1, 2, 3, 4, 5, 6, 7)
    }

    "rearranges array when the input given is not a min heap" in {
      BinaryHeap.minHeap(ArrayBuffer(7, 6, 5, 4, 3, 2, 1)).asArray ==== ArrayBuffer(1, 3, 2, 4, 6, 7, 5)
    }
  }

  "min" >> {
    "returns the min element" in {
      forAll(Gen.nonEmptyListOf(Arbitrary.arbitrary[Int])) { (xs: Seq[Int]) =>
        BinaryHeap.minHeap(ArrayBuffer(xs: _*)).peek ==== Option(xs.min)
      }
    }

    "returns None when heap is empty" in {
      BinaryHeap.minHeap(ArrayBuffer()).peek ==== None
    }
  }

  "extract min" >> {
    "returns the minimum element" in {
      forAll(Gen.nonEmptyListOf(Arbitrary.arbitrary[Int])) { (xs: Seq[Int]) =>
        BinaryHeap.minHeap(ArrayBuffer(xs: _*)).extract ==== Option(xs.min)
      }

      "rearranges array to be a min heap" in {
        val heap = BinaryHeap.minHeap(ArrayBuffer(7, 6, 5, 4, 3, 2, 1))

        heap.extract

        heap.asArray ==== ArrayBuffer(2, 3, 5, 4, 6, 7)
      }
    }

    "returns None when the heap is empty" in {
      BinaryHeap.minHeap(ArrayBuffer()).extract ==== None
    }
  }

  "add" >> {
    "maintains min heap invariant" in {
      val heap = BinaryHeap.minHeap(ArrayBuffer.empty[Int])

      heap.add(70)
      heap.asArray ==== ArrayBuffer(30, 65, 60, 70)

      heap.add(10)
      heap.asArray ==== ArrayBuffer(10, 30, 60, 70, 65)
    }
  }
}
