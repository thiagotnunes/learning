package com.learning.datastructures.mutable.heap

import com.learning.PropertySpecification
import com.learning.datastructures.mutable.array.DynamicArray
import org.scalacheck.Prop.forAll
import org.scalacheck.{Arbitrary, Gen}

class MinHeapSpec extends PropertySpecification {

  "heap creation" >> {
    "does nothing when array is already a min heap" in {
      new MinHeap(DynamicArray(1, 2, 3, 4, 5, 6, 7)).asArray ==== DynamicArray(1, 2, 3, 4, 5, 6, 7)
    }

    "rearranges array when the input given is not a min heap" in {
      new MinHeap(DynamicArray(7, 6, 5, 4, 3, 2, 1)).asArray ==== DynamicArray(1, 3, 2, 4, 6, 7, 5)
    }
  }

  "min" >> {
    "returns the min element" in {
      forAll(Gen.nonEmptyListOf(Arbitrary.arbitrary[Int])) { (xs: Seq[Int]) =>
        new MinHeap(DynamicArray.from(xs)).min ==== xs.min
      }
    }

    "returns null when heap is empty" in {
      new MinHeap(DynamicArray[Int]()).min ==== null.asInstanceOf[Int]
    }
  }

  "extract min" >> {
    "returns the minimum element" in {
      forAll(Gen.nonEmptyListOf(Arbitrary.arbitrary[Int])) { (xs: Seq[Int]) =>
        new MinHeap(DynamicArray.from(xs)).extractMin ==== xs.min
      }

      "rearranges array to be a min heap" in {
        val heap = new MinHeap(DynamicArray(7, 6, 5, 4, 3, 2, 1))

        heap.extractMin

        heap.asArray ==== DynamicArray(2, 3, 5, 4, 6, 7)
      }
    }

    "returns null when the heap is empty" in {
      new MinHeap(DynamicArray[Int]()).extractMin ==== null.asInstanceOf[Int]
    }
  }

  "add" >> {
    "maintains min heap invariant" in {
      val heap = new MinHeap[Int](DynamicArray.empty[Int])

      heap.add(70)
      heap.asArray ==== DynamicArray(70)

      heap.add(60)
      heap.asArray ==== DynamicArray(60, 70)

      heap.add(30)
      heap.asArray ==== DynamicArray(30, 70, 60)

      heap.add(65)
      heap.asArray ==== DynamicArray(30, 65, 60, 70)

      heap.add(10)
      heap.asArray ==== DynamicArray(10, 30, 60, 70, 65)
    }
  }
}
