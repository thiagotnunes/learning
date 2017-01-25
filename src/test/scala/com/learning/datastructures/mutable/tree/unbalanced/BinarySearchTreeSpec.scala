package com.learning.datastructures.mutable.tree.unbalanced

import com.learning.PropertySpecification
import com.learning.datastructures.mutable.tree.DepthFirst
import com.learning.datastructures.mutable.tree.bst.unbalanced.{BinarySearchTree, Node}
import org.scalacheck.Prop.forAll
import org.scalacheck.{Arbitrary, Gen}

class BinarySearchTreeSpec extends PropertySpecification {

  "add" >> {
    "adds elements to the tree" in {
      val tree = new BinarySearchTree[Int]()
      tree.add(2)
      tree.add(3)
      tree.add(1)

      tree.root ==== Some(Node(
        2,
        Some(Node.leaf(1)),
        Some(Node.leaf(3))
      ))
    }
  }

  "find" >> {
    "returns element when it is in the tree" in {
      BinarySearchTree
        .from(Seq(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
        .find(5) ==== Some(5)
    }

    "returns None when element is not in the tree" in {
      BinarySearchTree
        .from(Seq(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
        .find(0) ==== None
    }

    "returns None when searching in an empty tree" in {
      new BinarySearchTree().find(5) ==== None
    }
  }

  "remove" >> {
    "returns false when element to remove is not in the tree" in {
      //   5
      // 2
      val tree = BinarySearchTree.from(Seq(5, 2))

      tree.remove(1) ==== false

      tree.root ==== Some(Node(
        5,
        Some(Node.leaf(2)),
        None
      ))
      tree.size ==== 2
    }

    "returns false when trying to remove from empty tree" in {
      val tree = new BinarySearchTree[Int]()

      tree.remove(1) ==== false

      tree.root ==== None
      tree.size ==== 0
    }

    "removes root element" in {
      val tree = BinarySearchTree.from(Seq(1))

      tree.remove(1) ==== true

      tree.root ==== None
      tree.size ==== 0
    }

    "removes the root node with left child" in {
      val tree = BinarySearchTree.from(Seq(10, 5))

      tree.remove(10)

      tree.root ==== Some(Node(5, None, None))
    }

    "removes the root node with right child" in {
      val tree = BinarySearchTree.from(Seq(10, 15))

      tree.remove(10)

      tree.root ==== Some(Node(15, None, None))
    }

    "removes leaf element from a tree" in {
      //  5
      // 3 7
      val tree = BinarySearchTree.from(Seq(5, 3, 7))

      tree.remove(3) ==== true

      tree.root ==== Some(Node(
        5,
        None,
        Some(Node.leaf(7))
      ))
      tree.size ==== 2
    }

    "from left subtree" >> {
      "removes element with one left child from a tree" in {
        //    5
        //   3
        //  2
        // 1
        val tree = BinarySearchTree.from(Seq(5, 3, 2, 1))

        tree.remove(3) ==== true

        tree.root ==== Some(Node(
          5,
          Some(Node(
            2,
            Some(Node.leaf(1)),
            None
          )),
          None
        ))
        tree.size ==== 3
      }

      "removes element with one right child from a tree" in {
        //    5
        // 2
        //  3
        //   4
        val tree = BinarySearchTree.from(Seq(5, 2, 3, 4))

        tree.remove(2) ==== true

        tree.root ==== Some(Node(
          5,
          Some(Node(
            3,
            None,
            Some(Node.leaf(4))
          )),
          None
        ))
        tree.size ==== 3
      }

      "removes element with two children from a tree" in {
        //        7
        //    4
        //  2   5
        // 0 3   6
        val tree = BinarySearchTree.from(Seq(7, 4, 2, 5, 0, 3, 6))

        tree.remove(4) ==== true

        tree.root ==== Some(Node(
          7,
          Some(Node(
            3,
            Some(Node(
              2,
              Some(Node.leaf(0)),
              None
            )),
            Some(Node(
              5,
              None,
              Some(Node.leaf(6))
            ))
          )),
          None
        ))
        tree.size ==== 6
      }
    }

    "from right subtree" >> {
      "removes element with one left child from a tree" in {
        // 5
        //    8
        //   7
        //  6
        val tree = BinarySearchTree.from(Seq(5, 8, 7, 6))

        tree.remove(8) ==== true

        tree.root ==== Some(Node(
          5,
          None,
          Some(Node(
            7,
            Some(Node.leaf(6)),
            None
          ))
        ))
        tree.size ==== 3
      }

      "removes element with one right child from a tree" in {
        // 5
        //  8
        //   9
        //    10
        val tree = BinarySearchTree.from(Seq(5, 8, 9, 10))

        tree.remove(8) ==== true

        tree.root ==== Some(Node(
          5,
          None,
          Some(Node(
            9,
            None,
            Some(Node.leaf(10))
          ))
        ))
        tree.size ==== 3
      }

      "removes element with two children from a tree" in {
        // 7
        //      11
        //   9      15
        //  8 10  13  16
        val tree = BinarySearchTree.from(Seq(7, 11, 9, 15, 8, 10, 13, 16))

        tree.remove(11) ==== true

        tree.root ==== Some(Node(
          7,
          None,
          Some(Node(
            10,
            Some(Node(
              9,
              Some(Node.leaf(8)),
              None
            )),
            Some(Node(
              15,
              Some(Node.leaf(13)),
              Some(Node.leaf(16))
            ))
          ))
        ))
        tree.size ==== 7
      }
    }
  }

  "max" >> {
    "returns max element from the tree" in {
      forAll(Gen.nonEmptyListOf(Arbitrary.arbitrary[Int])) { (xs: Seq[Int]) =>
        BinarySearchTree.from(xs).max ==== Some(xs.max)
      }
    }

    "returns None when tree is empty" in {
      new BinarySearchTree[Int]().max ==== None
    }
  }

  "min" >> {
    "returns min element from the tree" in {
      forAll(Gen.nonEmptyListOf(Arbitrary.arbitrary[Int])) { (xs: Seq[Int]) =>
        BinarySearchTree.from(xs).min ==== Some(xs.min)
      }
    }

    "returns None when tree is empty" in {
      new BinarySearchTree[Int]().min ==== None
    }
  }

  "equals" >> {
    "two empty BSTs are equal" in {
      new BinarySearchTree[Int]() ==== new BinarySearchTree[Int]()
    }

    "two BSTs with the same elements are equal" in {
      forAll { (xs: Seq[Int]) =>
        BinarySearchTree.from(xs) ==== BinarySearchTree.from(xs)
      }
    }
  }

  "isValid (only for Bounded typed trees)" >> {
    "returns true if tree is empty" in {
      BinarySearchTree.isValid(None) ==== true
    }

    "returns true if tree is a single element tree" in {
      BinarySearchTree.isValid(Some(Node.leaf(1))) ==== true
    }

    "returns true if tree is binary search tree" in {
      val root = Some(Node(
        10,
        Some(Node(
          5,
          Some(Node(
            5,
            Some(Node.leaf(3)),
            None
          )),
          Some(Node.leaf(7))
        )),
        Some(Node(
          15,
          None,
          Some(Node.leaf(20))
        ))
      ))

      BinarySearchTree.isValid(root) ==== true
    }

    "returns false if tree is not binary search tree" in {
      val root = Some(Node(
        10,
        Some(Node(
          5,
          Some(Node(
            5,
            Some(Node.leaf(3)),
            None
          )),
          Some(Node.leaf(7))
        )),
        Some(Node(
          15,
          None,
          // problem is here
          Some(Node.leaf(0))
        ))
      ))

      BinarySearchTree.isValid(root) ==== false
    }
  }
}
