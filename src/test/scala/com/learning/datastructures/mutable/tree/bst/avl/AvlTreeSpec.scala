package com.learning.datastructures.mutable.tree.bst.avl

import com.learning.PropertySpecification
import com.learning.datastructures.mutable.tree.DepthFirst
import org.scalacheck.Prop.forAll
import org.scalacheck.{Arbitrary, Gen}

class AvlTreeSpec extends PropertySpecification {

  "add" >> {
    "adds root node if there is none" in {
      val tree = AvlTree.getInstance[Int]

      tree.add(1)

      tree.root ==== Some(Node.leaf(1))
    }

    "adds element to the left when it is lesser than root and updates heights" in {
      val tree = AvlTree.getInstance[Int]

      tree.add(10)
      tree.add(5)

      tree.root ==== Some(Node(10, Some(Node.leaf(5)), None, 2))
    }

    "adds element to the right when it is larger than root and updates heights" in {
      val tree = AvlTree.getInstance[Int]

      tree.add(10)
      tree.add(15)

      tree.root ==== Some(Node(10, None, Some(Node.leaf(15)), 2))
    }

    "right rotate on LL case" in {
      val tree = AvlTree.getInstance[Int]

      tree.add(20)
      tree.add(10)
      tree.add(5)

      tree.root ==== Some(Node(10, Some(Node.leaf(5)), Some(Node.leaf(20)), 2))
    }

    "left rotate on RR case" in {
      val tree = AvlTree.getInstance[Int]

      tree.add(5)
      tree.add(10)
      tree.add(20)

      tree.root ==== Some(Node(10, Some(Node.leaf(5)), Some(Node.leaf(20)), 2))
    }

    "left and right rotate on LR case" in {
      val tree = AvlTree.getInstance[Int]

      tree.add(20)
      tree.add(10)
      tree.add(15)

      tree.root ==== Some(Node(15, Some(Node.leaf(10)), Some(Node.leaf(20)), 2))
    }

    "right and left rotate on RL case" in {
      val tree = AvlTree.getInstance[Int]

      tree.add(10)
      tree.add(20)
      tree.add(15)

      tree.root ==== Some(Node(15, Some(Node.leaf(10)), Some(Node.leaf(20)), 2))
    }

    "maintains tree in order" in {
      val search = new DepthFirst
      forAll { (xs: Seq[Int]) =>
        val tree = AvlTree.getInstance[Int]
        xs.foreach(tree.add)

        search.traverse(tree.root)(DepthFirst.inOrder) ==== xs.sorted
      }
    }

    // I am not 100% sure this is true, I think there might be a constant hiding in O(n),
    // but so far tests did not show it. I will have to further investigate
    "contains height of the tree to be O(logn)" in {
      forAll(Gen.nonEmptyListOf(Arbitrary.arbitrary[Int])) { (xs: Seq[Int]) =>
        val tree = AvlTree.getInstance[Int]
        xs.foreach(tree.add)

        tree.root.map(_.height).getOrElse(0) must be_<=(1 + (Math.log10(xs.size) / Math.log10(2)).ceil.toInt)
      }
    }
  }

  "size" >> {
    "maintains tree size" in {
      forAll { (xs: Seq[Int]) =>
        val tree = AvlTree.getInstance[Int]
        xs.foreach(tree.add)

        tree.size ==== xs.size
      }
    }
  }
}
