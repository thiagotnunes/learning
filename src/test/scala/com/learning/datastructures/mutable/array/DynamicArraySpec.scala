package com.learning.datastructures.mutable.array

import com.learning.PropertySpecification
import org.scalacheck.Arbitrary.arbitrary
import org.scalacheck.Gen.nonEmptyListOf
import org.scalacheck.Prop.forAll
import org.scalacheck.{Arbitrary, Gen}

import scala.reflect.ClassTag

class DynamicArraySpec extends PropertySpecification {

  implicit def arbitraryDynamicArray[T: Arbitrary : ClassTag] = Arbitrary({
    for {
      xs <- Gen.listOf(arbitrary[T])
    } yield {
      DynamicArray.from(xs)
    }
  })

  "add" >> {
    "adds the element to the array" in {
      val array = new DynamicArray[Int](1)
      array.add(1)

      array.size ==== 1
      array.capacity ==== 1
      array ==== DynamicArray(1)
    }

    "expands the array when capacity has been reached" in {
      val array = new DynamicArray[Int](1)
      array.add(1)
      array.add(2)

      array.capacity ==== 2
      array ==== DynamicArray(1, 2)
    }
  }

  "removeLast" >> {
    "removes an element from the array" in {
      val array = new DynamicArray[Int](2)
      array.add(1)
      array.add(2)

      array.removeLast() ==== 2
      array.size ==== 1
      array ==== DynamicArray(1)
    }

    "throws exception when removing from an empty array" in {
      val array = new DynamicArray[Int](1)

      array.removeLast() must throwA[ArrayIndexOutOfBoundsException]
    }
  }

  "remove" >> {
    "removes an element from the array at the given index" in {
      val array = new DynamicArray[Int](3)
      array.add(1)
      array.add(2)
      array.add(3)

      array.remove(1) ==== 2
      array ==== DynamicArray(1, 3)
    }
  }

  "get" >> {
    "returns the element in the given position" in {
      val array = new DynamicArray[Int](3)
      array.add(5)
      array.add(4)
      array.add(3)

      array.get(1) ==== 4
      array ==== DynamicArray(5, 4, 3)
    }

    "throws array index out of bounds when position is not in array" in {
      val array = new DynamicArray[Int](3)
      array.add(5)

      array.get(1) must throwA[ArrayIndexOutOfBoundsException]
    }
  }

  "reverse" >> {
    "returns the reversed array" in {
      DynamicArray(1, 2, 3, 4, 5).reverse() ==== DynamicArray(5, 4, 3, 2, 1)
    }

    "double reversing returns the original array" in {
      forAll { (xs: DynamicArray[Int]) =>
        DynamicArray(xs).reverse().reverse() ==== DynamicArray(xs)
      }
    }
  }

  "equals and hashCode" >> {
    "two arrays with the same elements must be equal" in {
      forAll { (xs: Seq[Int]) =>
        DynamicArray.from(xs) ==== DynamicArray.from(xs)
      }
    }

    "two arrays with the same elements must have the same hash code" in {
      forAll { (xs: Seq[Int]) =>
        DynamicArray.from(xs).hashCode() ==== DynamicArray.from(xs).hashCode()
      }
    }

    "two arrays with different elements must not be equal" in {
      forAll(nonEmptyListOf(arbitrary[Int]), nonEmptyListOf(arbitrary[Int])) {
        (xs: List[Int], ys: List[Int]) =>
          xs != ys ==>
            (DynamicArray.from(xs) !=== DynamicArray.from(ys))
      }
    }
  }
}
