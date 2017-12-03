package com.learning.algorithms.dynamicprogramming

import org.specs2.mutable.Specification

class LongestSubsequenceSpec extends Specification {

  private val problem = new LongestSubsequence

  "returns the longest subsequence of two strings" in {
    problem.longestSubsequence(
      "abcdefghijklmnopqrstuvwxyz",
      "bdfgimpabxxwz"
    ) ==== (9, "bdfgimpwz")
  }

  "returns 0 when there is no common subsequence between two strings" in {
    problem.longestSubsequence(
      "abcdef",
      "hijklm"
    ) ==== (0, "")
  }
}
