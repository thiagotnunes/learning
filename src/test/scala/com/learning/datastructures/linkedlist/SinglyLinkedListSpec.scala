package com.learning.datastructures.linkedlist

import com.learning.PropertySpecification
import org.scalacheck.{Arbitrary, Gen, Prop}
import org.scalacheck.Arbitrary.arbitrary
import org.scalacheck.Gen.{choose, nonEmptyListOf}
import org.scalacheck.Prop.forAll

class SinglyLinkedListSpec extends PropertySpecification {

  implicit def arbitraryList[T: Arbitrary] = Arbitrary({
    for {
      xs <- Gen.listOf(arbitrary[T])
    } yield {
      SinglyLinkedList.from(xs)
    }
  })

  "add" >> {
    "adds an element to the list" in {
      val list = SinglyLinkedList[Int]()
      list.add(1)

      list.size ==== 1
      list ==== SinglyLinkedList(1)
    }
  }

  "size" >> {
    "size of a non-empty list is the number of elements within" in {
      forAll { (xs: Seq[Int]) =>
        val list = SinglyLinkedList.from(xs)

        list.size ==== xs.size
      }
    }

    "size of an empty list is 0" in {
      SinglyLinkedList[Int]().size ==== 0
    }
  }

  "removeFirst" >> {
    "removes the first element from the list" in {
      forAll(nonEmptyListOf(arbitrary[Int])) { (xs: List[Int]) =>
        val list = SinglyLinkedList.from(xs)
        val removed = list.removeFirst()

        removed ==== xs.head
        list.size ==== xs.size - 1
        list ==== SinglyLinkedList.from(xs.tail)
      }
    }

    "throws an exception when the list is empty" in {
      SinglyLinkedList[Int]().removeFirst() must throwA[IndexOutOfBoundsException]
    }
  }

  "remove" >> {
    "removes the given index from the list" in {
      val nonEmptyListAndRandomIndex = for {
        xs <- nonEmptyListOf(arbitrary[Int])
        i <- choose(0, xs.size - 1)
      } yield {
        (xs, i)
      }

      forAll(nonEmptyListAndRandomIndex)({ case (xs, i) =>
        val list = SinglyLinkedList.from(xs)
        val removed = list.remove(i)

        removed ==== xs(i)
        list.size ==== xs.size - 1
        list ==== SinglyLinkedList.from(xs.take(i) ++ xs.drop(i + 1))
      })
    }

    "remove" in {
      SinglyLinkedList(1).remove(0) ==== 1
    }
  }

  "equals" >> {
    "two lists with no elements should be equal" in {
      SinglyLinkedList() ==== SinglyLinkedList()
    }

    "two lists with same elements should be equal" in {
      forAll { (xs: Seq[Int]) =>
        SinglyLinkedList.from(xs) ==== SinglyLinkedList.from(xs)
      }
    }

    "two lists with different elements should not be equal" in {
      forAll(nonEmptyListOf(arbitrary[Int]), nonEmptyListOf(arbitrary[Int])) {
        (xs: List[Int], ys: List[Int]) =>
          xs != ys ==>
            (SinglyLinkedList.from(xs) !=== SinglyLinkedList.from(ys))
      }
    }
  }

  "hashCode" in {
    "two lists with same elements should have the same hash code" in {
      forAll { (xs: Seq[Int]) =>
        SinglyLinkedList.from(xs).hashCode ==== SinglyLinkedList.from(xs).hashCode
      }
    }
  }
}
