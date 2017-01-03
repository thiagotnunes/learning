package com.learning.datastructures.mutable.linkedlist

import com.learning.PropertySpecification
import org.scalacheck.{Arbitrary, Gen, Prop}
import org.scalacheck.Arbitrary.arbitrary
import org.scalacheck.Gen.{choose, nonEmptyListOf}
import org.scalacheck.Prop.forAll

class LinkedListSpec extends PropertySpecification {

  implicit def arbitraryList[T: Arbitrary] = Arbitrary({
    for {
      xs <- Gen.listOf(arbitrary[T])
    } yield {
      LinkedList.from(xs)
    }
  })

  "add" >> {
    "adds an element to the list" in {
      val list = LinkedList[Int]()
      list.add(1)

      list.size ==== 1
      list ==== LinkedList(1)
    }
  }

  "size" >> {
    "size of a non-empty list is the number of elements within" in {
      forAll { (xs: Seq[Int]) =>
        val list = LinkedList.from(xs)

        list.size ==== xs.size
      }
    }

    "size of an empty list is 0" in {
      LinkedList[Int]().size ==== 0
    }
  }

  "removeFirst" >> {
    "removes the first element from the list" in {
      forAll(nonEmptyListOf(arbitrary[Int])) { (xs: List[Int]) =>
        val list = LinkedList.from(xs)
        val removed = list.removeFirst()

        removed ==== xs.head
        list.size ==== xs.size - 1
        list ==== LinkedList.from(xs.tail)
      }
    }

    "throws an exception when the list is empty" in {
      LinkedList[Int]().removeFirst() must throwA[IndexOutOfBoundsException]
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
        val list = LinkedList.from(xs)
        val removed = list.remove(i)

        removed ==== xs(i)
        list.size ==== xs.size - 1
        list ==== LinkedList.from(xs.take(i) ++ xs.drop(i + 1))
      })
    }

    "throws an exception when the list is empty" in {
      LinkedList[Int]().remove(0) must throwA[IndexOutOfBoundsException]
    }

    "throws an exception when trying to remove non-existing index" in {
      LinkedList(1, 2, 3).remove(3) must throwA[IndexOutOfBoundsException]
    }
  }

  "get" >> {
    "returns the element at the given position" in {
      val nonEmptyListAndRandomIndex = for {
        xs <- nonEmptyListOf(arbitrary[Int])
        i <- choose(0, xs.size - 1)
      } yield {
        (xs, i)
      }

      forAll(nonEmptyListAndRandomIndex)({ case (xs, i) =>
        LinkedList.from(xs).get(i) ==== xs(i)
      })
    }

    "throws an exception when trying to get non-existing index" in {
      LinkedList(1, 2, 3).get(3) must throwA[IndexOutOfBoundsException]
    }

    "throws an exception when trying to get from empty list" in {
      LinkedList[Int]().get(0) must throwA[IndexOutOfBoundsException]
    }
  }

  "reverse" >> {
    "returns the reversed list" in {
      LinkedList(1, 2, 3).reverse() ==== LinkedList(3, 2, 1)
    }

    "double reversing returns the original list" in {
      forAll { (xs: Seq[Int]) =>
        LinkedList.from(xs).reverse().reverse() ==== LinkedList.from(xs)
      }
    }
  }

  "equals" >> {
    "two lists with no elements should be equal" in {
      LinkedList() ==== LinkedList()
    }

    "two lists with same elements should be equal" in {
      forAll { (xs: Seq[Int]) =>
        LinkedList.from(xs) ==== LinkedList.from(xs)
      }
    }

    "two lists with different elements should not be equal" in {
      forAll(nonEmptyListOf(arbitrary[Int]), nonEmptyListOf(arbitrary[Int])) {
        (xs: List[Int], ys: List[Int]) =>
          xs != ys ==>
            (LinkedList.from(xs) !=== LinkedList.from(ys))
      }
    }
  }

  "hashCode" in {
    "two lists with same elements should have the same hash code" in {
      forAll { (xs: Seq[Int]) =>
        LinkedList.from(xs).hashCode ==== LinkedList.from(xs).hashCode
      }
    }
  }
}
