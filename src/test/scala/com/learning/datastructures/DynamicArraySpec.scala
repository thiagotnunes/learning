package com.learning.datastructures

import org.specs2.mutable.Specification

class DynamicArraySpec extends Specification {

  "add" >> {
    "adds the element to the array" in {
      val array = new DynamicArray[Int](1)
      array.add(1)
      array.size ==== 1
      array.currentCapacity ==== 1
    }

    "expands the array when capacity has been reached" in {
      val array = new DynamicArray[Int](1)
      array.add(1)
      array.add(2)
      array.currentCapacity ==== 2
    }
  }

  "remove" >> {
    "removes an element from the array" in {
      val array = new DynamicArray[Int](2)
      array.add(1)
      array.add(2)
      array.remove() ==== 2
      array.size ==== 1
    }

    "throws exception when removing from an empty array" in {
      val array = new DynamicArray[Int](1)
      array.remove() must throwA[IllegalStateException]
    }
  }

  "get" >> {
    "returns the element in the given position" in {
      val array = new DynamicArray[Int](3)
      array.add(5)
      array.add(4)
      array.add(3)
      array.get(1) ==== 4
    }

    "throws array index out of bounds when position is not in array" in {
      val array = new DynamicArray[Int](3)
      array.add(5)
      array.get(1) must throwA[ArrayIndexOutOfBoundsException]
    }
  }

  "reverse" >> {
    "returns the reversed array" in {
      val array = DynamicArray(1, 2, 3, 4, 5)
      array.reverse() ==== DynamicArray(5, 4, 3, 2, 1)
    }
  }

  "equality" >> {
    "two arrays with the same elements must be equal" in {
      DynamicArray(1, 2, 3) ==== DynamicArray(1, 2, 3)
    }

    "two arrays with the same elements must have the same hash code" in {
      DynamicArray(1, 2, 3).hashCode() ==== DynamicArray(1, 2, 3).hashCode()
    }

    "two array with different elements must not be equal" in {
      DynamicArray(1, 2, 3) !=== DynamicArray(1, 2, 3, 4)
    }

    "two array with different elements must not have the same hash code" in {
      DynamicArray(1, 2, 3).hashCode() !=== DynamicArray(1, 2, 3, 4).hashCode()
    }
  }

}
