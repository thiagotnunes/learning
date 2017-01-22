package com.learning.datastructures.mutable.tree.bst.avl

import org.specs2.mutable.Specification

class RotatorSpec extends Specification {

  val heightCalculator = new HeightCalculator
  val rotator = new Rotator(heightCalculator)

  "rotateLeft" >> {
    "returns updated root node" in {
      //  10               20
      // 5  20     =>  10      30
      //  15  30      5  15  25  35
      //    25  35
      val root = Node(
        10,
        Some(Node.leaf(5)),
        Some(Node(
          20,
          Some(Node.leaf(15)),
          Some(Node(
            30,
            Some(Node.leaf(25)),
            Some(Node.leaf(35)),
            2
          )),
          3
        )),
        4
      )

      rotator.rotateLeft(root) ==== Node(
        20,
        Some(Node(
          10,
          Some(Node.leaf(5)),
          Some(Node.leaf(15)),
          2
        )),
        Some(Node(
          30,
          Some(Node.leaf(25)),
          Some(Node.leaf(35)),
          2
        )),
        3
      )
    }

    "returns updated root node for simple tree" in {
      //  10          20
      //    20  =>  10
      val root = Node(
        10,
        None,
        Some(Node.leaf(20)),
        2
      )

      rotator.rotateLeft(root) ==== Node(
        20,
        Some(Node.leaf(10)),
        None,
        2
      )

    }
  }

  "rotateRight" >> {
    "returns updated root node" in {
      //      30            20
      //    20  35  =>  10      30
      //  10  25       5  15  25  35
      // 5  15
      val root = Node(
        30,
        Some(Node(
          20,
          Some(Node(
            10,
            Some(Node.leaf(5)),
            Some(Node.leaf(15)),
            2
          )),
          Some(Node.leaf(25)),
          3
        )),
        Some(Node.leaf(35)),
        4
      )

      rotator.rotateRight(root) ==== Node(
        20,
        Some(Node(
          10,
          Some(Node.leaf(5)),
          Some(Node.leaf(15)),
          2
        )),
        Some(Node(
          30,
          Some(Node.leaf(25)),
          Some(Node.leaf(35)),
          2
        )),
        3
      )
    }

    "returns updated root node for simple tree" in {
      //   20      10
      // 10    =>    20
      val root = Node(
        20,
        Some(Node.leaf(10)),
        None,
        2
      )

      rotator.rotateRight(root) ==== Node(
        10,
        None,
        Some(Node.leaf(20)),
        2
      )

    }
  }

}
