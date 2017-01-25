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

    "contains height of the tree to be less than O(n)" in {
      forAll(Gen.nonEmptyListOf(Arbitrary.arbitrary[Int])) { (xs: Seq[Int]) =>
        val tree = AvlTree.getInstance[Int]
        xs.foreach(tree.add)

        tree.root.map(_.height).getOrElse(0) must be_<=(xs.size)
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

  "find" >> {
    "find" >> {
      "returns element when it is in the tree" in {
        AvlTree
          .from(Seq(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
          .find(5) ==== Some(5)
      }

      "returns None when element is not in the tree" in {
        AvlTree
          .from(Seq(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
          .find(0) ==== None
      }

      "returns None when searching in an empty tree" in {
        AvlTree.getInstance[Int].find(5) ==== None
      }
    }
  }

  "remove" >> {
    "returns false when element to remove is not in the tree" in {
      //   5
      // 2
      val tree = AvlTree.from(Seq(5, 2))

      tree.remove(1) ==== false

      tree.root ==== Some(Node(5, Some(Node.leaf(2)), None, 2))
      tree.size ==== 2
    }

    "returns false when trying to remove from empty tree" in {
      val tree = AvlTree.getInstance[Int]

      tree.remove(1) ==== false

      tree.size ==== 0
    }

    "removes root element" in {
      val tree = AvlTree.from(Seq(1))

      tree.remove(1) ==== true

      tree.root ==== None
      tree.size ==== 0
    }

    "removes the root node with left child" in {
      val tree = AvlTree.from(Seq(10, 5))

      tree.remove(10)

      tree.root ==== Some(Node.leaf(5))
    }

    "removes the root node with right child" in {
      val tree = AvlTree.from(Seq(10, 15))

      tree.remove(10)

      tree.root ==== Some(Node.leaf(15))
    }

    "removes leaf element from a tree" in {
      //  5
      // 3 7
      val tree = AvlTree.from(Seq(5, 3, 7))

      tree.remove(3) ==== true

      tree.root ==== Some(Node(5, None, Some(Node.leaf(7)), 2))
      tree.size ==== 2
    }

    "from left subtree" >> {
      "removes element with one left child from a tree" in {
        //    5
        //   3 7
        //  2   9
        val tree = AvlTree.from(Seq(5, 3, 7, 2, 9))

        tree.remove(3) ==== true

        tree.root ==== Some(Node(5, Some(Node.leaf(2)), Some(Node(7, None, Some(Node.leaf(9)), 2)), 3))
        tree.size ==== 4
      }

      "removes element with one right child from a tree" in {
        //     5
        //   3   7
        //    4   9
        val tree = AvlTree.from(Seq(5, 3, 7, 4, 9))

        tree.remove(3) ==== true

        tree.root ==== Some(Node(5, Some(Node.leaf(4)), Some(Node(7, None, Some(Node.leaf(9)), 2)), 3))
        tree.size ==== 4
      }
    }

    "from right subtree" >> {
      "removes element with one left child from a tree" in {
        //     5
        //   3   7
        //  2   6
        val tree = AvlTree.from(Seq(5, 3, 7, 2, 6))

        tree.remove(7) ==== true

        tree.root ==== Some(Node(5, Some(Node(3, Some(Node.leaf(2)), None, 2)), Some(Node.leaf(6)), 3))
        tree.size ==== 4
      }

      "removes element with one right child from a tree" in {
        //     5
        //   3   7
        //  2     9
        val tree = AvlTree.from(Seq(5, 3, 7, 2, 9))

        tree.remove(7) ==== true

        tree.root ==== Some(Node(5, Some(Node(3, Some(Node.leaf(2)), None, 2)), Some(Node.leaf(9)), 3))
        tree.size ==== 4
      }

      "rebalances the tree" in {
        //     5
        //   3   7
        //        9
        val tree = AvlTree.from(Seq(5, 3, 7, 9))

        tree.remove(3)

        tree.root ==== Some(Node(7, Some(Node.leaf(5)), Some(Node.leaf(9)), 2))
        tree.size ==== 3
      }
    }

    "rebalances the tree" in {
      /*
                         500 (5)
            250 (3)                   750 (4)
       200 (1) 300 (2)         700 (2)       900 (3)
                 350 (1)         725 (1)  850 (1) 950 (2)
                                                    975 (1)

       After rebalancing

                                          750 (4)
                       500 (3)                               900 (3)
            300 (2)             700 (2)                   850 (1) 950 (2)
        250 (1)  350(1)           725 (1)                          975 (1)
      */
      val tree = AvlTree.from(Seq(500, 250, 750, 200, 300, 700, 900, 350, 725, 850, 950, 975))

      tree.remove(200)

      tree.root ==== Some(Node(
        750,
        Some(Node(
          500,
          Some(Node(
            300,
            Some(Node.leaf(250)),
            Some(Node.leaf(350)),
            2
          )),
          Some(Node(
            700,
            None,
            Some(Node.leaf(725)),
            2
          )),
          3
        )),
        Some(Node(
          900,
          Some(Node.leaf(850)),
          Some(Node(
            950,
            None,
            Some(Node.leaf(975)),
            2
          )),
          3
        )),
        4
      ))
      tree.size ==== 11
    }
  }

}
