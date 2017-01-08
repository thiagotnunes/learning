package com.learning.datastructures.mutable.heap

import com.learning.PropertySpecification
import com.learning.datastructures.mutable.array.DynamicArray
import org.scalacheck.Prop.forAll
import org.scalacheck.{Arbitrary, Gen}

class MaxHeapSpec extends PropertySpecification {

  "heap creation" >> {
    "does nothing when array is already a max heap" in {
      new MaxHeap(DynamicArray(7, 6, 5, 4, 3, 2, 1)).asArray ==== DynamicArray(7, 6, 5, 4, 3, 2, 1)
    }

    "rearranges array when the input given is not a max heap" in {
      new MaxHeap(DynamicArray(1, 2, 3, 4, 5, 6, 7)).asArray ==== DynamicArray(7, 5, 6, 4, 2, 1, 3)
    }
  }

  "max" >> {
    "returns the max element" in {
      forAll(Gen.nonEmptyListOf(Arbitrary.arbitrary[Int])) { (xs: Seq[Int]) =>
        new MaxHeap[Int](DynamicArray.from(xs)).max ==== Option(xs.max)
      }
    }

    "returns None when heap is empty" in {
      new MaxHeap(DynamicArray[Int]()).max ==== None
    }
  }

  "extract max" >> {
    "returns the maximum element" in {
      forAll(Gen.nonEmptyListOf(Arbitrary.arbitrary[Int])) { (xs: Seq[Int]) =>
        new MaxHeap(DynamicArray.from(xs)).extractMax ==== Option(xs.max)
      }

      "rearranges array to be a max heap" in {
        val heap = new MaxHeap(DynamicArray(1, 2, 3, 4, 5, 6, 7))

        heap.extractMax

        heap.asArray ==== DynamicArray(6, 5, 3, 4, 2, 1)
      }
    }

    "returns None when the heap is empty" in {
      new MaxHeap[Int](DynamicArray[Int]()).extractMax ==== None
    }
  }

  "add" >> {
    "maintains max heap invariant" in {
      val heap = new MaxHeap[Int](DynamicArray.empty[Int])

      heap.add(10)
      heap.asArray ==== DynamicArray(10)

      heap.add(20)
      heap.asArray ==== DynamicArray(20, 10)

      heap.add(30)
      heap.asArray ==== DynamicArray(30, 10, 20)

      heap.add(15)
      heap.asArray ==== DynamicArray(30, 15, 20, 10)

      heap.add(40)
      heap.asArray ==== DynamicArray(40, 30, 20, 10, 15)
    }
  }
}
