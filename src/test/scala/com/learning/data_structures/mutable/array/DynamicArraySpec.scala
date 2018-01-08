package com.learning.data_structures.mutable.array

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
    "sets initial capacity to default when 0 capacity is given" in {
      val array = new DynamicArray[Int](0)

      array.add(1)

      array.size ==== 1
      array.capacity ==== 100
      array ==== DynamicArray(1)
    }

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

      array.removeLast() ==== Some(2)
      array.size ==== 1
      array ==== DynamicArray(1)
    }

    "returns None when removing from an empty array" in {
      val array = new DynamicArray[Int](1)

      array.removeLast() ==== None
    }
  }

  "remove" >> {
    "removes an element from the array at the given index" in {
      val array = new DynamicArray[Int](3)

      array.add(1)
      array.add(2)
      array.add(3)

      array.remove(1) ==== Some(2)
      array ==== DynamicArray(1, 3)
    }
  }

  "get" >> {
    "returns the element in the given position" in {
      val array = new DynamicArray[Int](3)

      array.add(5)
      array.add(4)
      array.add(3)

      array.get(1) ==== Some(4)
      array ==== DynamicArray(5, 4, 3)
    }

    "returns none when position is not in array" in {
      val array = new DynamicArray[Int](3)

      array.add(5)

      array.get(1) ==== None
    }
  }

  "reverse" >> {
    "returns the reversed array" in {
      val array = DynamicArray(1, 2, 3, 4, 5)

      array.reverse()

      array ==== DynamicArray(5, 4, 3, 2, 1)
    }

    "double reversing returns the original array" in {
      forAll { (xs: Seq[Int]) =>
        val array = DynamicArray.from(xs)

        array.reverse()
        array.reverse()

        array ==== DynamicArray.from(xs)
      }
    }
  }

  "equals and hashCode" >> {
    "two empty arrays must be equal" in {
      DynamicArray.from(Seq.empty[Int]) ==== DynamicArray.from(Seq.empty[Int])
    }

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
