package com.learning.data_structures.mutable.linked_list

import com.learning.PropertySpecification
import org.scalacheck.Arbitrary.arbitrary
import org.scalacheck.Gen.{choose, nonEmptyListOf}
import org.scalacheck.Prop.forAll
import org.scalacheck.{Arbitrary, Gen}

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

        removed ==== Some(xs.head)
        list.size ==== xs.size - 1
        list ==== LinkedList.from(xs.tail)
      }
    }

    "returns None when the list is empty" in {
      LinkedList[Int]().removeFirst() ==== None
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

        removed ==== Some(xs(i))
        list.size ==== xs.size - 1
        list ==== LinkedList.from(xs.take(i) ++ xs.drop(i + 1))
      })
    }

    "returns None when the list is empty" in {
      LinkedList[Int]().remove(0) ==== None
    }

    "returns None when trying to remove non-existing index" in {
      LinkedList(1, 2, 3).remove(3) ==== None
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
        LinkedList.from(xs).get(i) ==== Option(xs(i))
      })
    }

    "returns None when trying to get non-existing index" in {
      LinkedList(1, 2, 3).get(3) ==== None
    }

    "returns None when trying to get from empty list" in {
      LinkedList[Int]().get(0) ==== None
    }
  }

  "reverse" >> {
    "returns the reversed list" in {
      val list = LinkedList(1, 2, 3)

      list.reverse()

      list ==== LinkedList(3, 2, 1)
    }

    "double reversing returns the original list" in {
      forAll { (xs: Seq[Int]) =>
        val list = LinkedList.from(xs)

        list.reverse()
        list.reverse()

        list ==== LinkedList.from(xs)
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
