package com.learning.datastructures.mutable.tree.bst.avl

import com.learning.PropertySpecification
import com.learning.datastructures.mutable.tree.DepthFirst
import org.scalacheck.Prop.forAll
import org.scalacheck.{Arbitrary, Gen}
import org.specs2.specification.Scope

class AvlTreeSpec extends PropertySpecification {

  trait Context extends Scope {
    val heightCalculator = new HeightCalculator
    val rotator = new Rotator(heightCalculator)
    val tree = new AvlTree[Int](rotator, heightCalculator)
  }

  "add" >> {
    "adds root node if there is none" in new Context {
      tree.add(1)

      tree.root ==== Some(Node.leaf(1))
    }

    "adds element to the left when it is lesser than root and updates heights" in new Context {
      tree.add(10)
      tree.add(5)

      tree.root ==== Some(Node(10, Some(Node.leaf(5)), None, 2))
    }

    "adds element to the right when it is larger than root and updates heights" in new Context {
      tree.add(10)
      tree.add(15)

      tree.root ==== Some(Node(10, None, Some(Node.leaf(15)), 2))
    }

    "right rotate on LL case" in new Context {
      tree.add(20)
      tree.add(10)
      tree.add(5)

      tree.root ==== Some(Node(10, Some(Node.leaf(5)), Some(Node.leaf(20)), 2))
    }

    "left rotate on RR case" in new Context {
      tree.add(5)
      tree.add(10)
      tree.add(20)

      tree.root ==== Some(Node(10, Some(Node.leaf(5)), Some(Node.leaf(20)), 2))
    }

    "left and right rotate on LR case" in new Context {
      tree.add(20)
      tree.add(10)
      tree.add(15)

      tree.root ==== Some(Node(15, Some(Node.leaf(10)), Some(Node.leaf(20)), 2))
    }

    "right and left rotate on RL case" in new Context {
      tree.add(10)
      tree.add(20)
      tree.add(15)

      tree.root ==== Some(Node(15, Some(Node.leaf(10)), Some(Node.leaf(20)), 2))
    }

    "maintains tree in order" in {
      val search = new DepthFirst
      val heightCalculator = new HeightCalculator
      val rotator = new Rotator(heightCalculator)
      forAll { (xs: Seq[Int]) =>
        val tree = new AvlTree[Int](rotator, heightCalculator)
        xs.foreach(tree.add)

        search.traverse(tree.root)(DepthFirst.inOrder) ==== xs.sorted
      }
    }

    // I am not 100% sure this is true, I think there might be a constant hiding in O(n),
    // but so far tests did not show it. I will have to further investigate
    "contains height of the tree to be O(logn)" in {
      val heightCalculator = new HeightCalculator
      val rotator = new Rotator(heightCalculator)
      forAll(Gen.nonEmptyListOf(Arbitrary.arbitrary[Int])) { (xs: Seq[Int]) =>
        val tree = new AvlTree[Int](rotator, heightCalculator)
        xs.foreach(tree.add)

        tree.root.map(_.height).getOrElse(0) must be_<=(1 + (Math.log10(xs.size) / Math.log10(2)).ceil.toInt)
      }
    }
  }
}
