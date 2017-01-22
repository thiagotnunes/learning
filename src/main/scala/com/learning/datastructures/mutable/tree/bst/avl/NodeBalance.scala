package com.learning.datastructures.mutable.tree.bst.avl

sealed trait NodeBalance

case object LeftLeft extends NodeBalance

case object LeftRight extends NodeBalance

case object RightLeft extends NodeBalance

case object RightRight extends NodeBalance

case object Balanced extends NodeBalance
