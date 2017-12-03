package com.learning.algorithms.dynamicprogramming

class LongestSubsequence {
  /**
    * n - the length of the first string
    * m - the length of the second string
    *
    * Time complexity - O(nm)
    * Space complexity - O(nm)
    */
  def longestSubsequence(s1: String, s2: String): (Int, String) = {
    val n = s1.length // O(1)
    val m = s2.length // O(1)
    val memo = Array.fill(n + 1, m + 1)(0) // O(nm)

    // O(nm)
    for (i <- 1.until(n + 1)) {
      val c = s1.charAt(i - 1)
      for (j <- 1.until(m + 1)) {
        if (c == s2.charAt(j - 1)) {
          memo(i)(j) = memo(i - 1)(j - 1) + 1
        } else {
          memo(i)(j) = Math.max(memo(i - 1)(j), memo(i)(j - 1))
        }
      }
    }

    val buffer = new StringBuffer() // O(max(n, m)) space
    var i = n
    var j = m

    // O(max(n, m))
    while (i != 0 && j != 0) {
      if (memo(i)(j) == memo(i - 1)(j)) {
        i = i - 1
      } else if (memo(i)(j) == memo(i)(j - 1)) {
        j = j - 1
      } else {
        buffer.append(s1.charAt(i - 1))
        i = i - 1
        j = j - 1
      }
    }
    (memo(n)(m), buffer.reverse().toString)
  }
}
