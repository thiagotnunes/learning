package com.learning.algorithms.strings.matcher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class KnuthMorrisPrattMatcher implements Matcher {
    /**
     * Time complexity - O(m + n)
     * Space complexity - O(m)
     */
    @Override
    public List<Integer> match(String text, String pattern) {
        int n = Optional.ofNullable(text).map(String::length).orElse(0);
        int m = Optional.ofNullable(pattern).map(String::length).orElse(0);
        List<Integer> matches = new ArrayList<>();

        if (m > n) return matches;
        if (n == 0) return matches;
        if (m == 0) return matches;

        int[] kmp = buildKmpFor(pattern, m);
        int i = 0;
        int j = 0;
        while (i < n) {
            char textChar = text.charAt(i);
            char patternChar = pattern.charAt(j);

            if (textChar == patternChar) {
                i++;
                j++;

                if (j == m) {
                    matches.add(i - m);
                    j = kmp[j - 1] + 1;
                }
            } else if (j > 0) {
                j = kmp[j - 1] + 1;
            } else {
                i++;
            }
        }

        return matches;
    }

    private int[] buildKmpFor(String pattern, int m) {
        int[] kmp = new int[m];
        Arrays.fill(kmp, -1);
        int i = 1;
        int j = 0;
        while (i < m) {
            char previous = pattern.charAt(j);
            char current = pattern.charAt(i);
            if (previous == current) {
                kmp[i] = kmp[j] + 1;
                i++;
                j++;
            } else if (j > 0) {
                j = kmp[j - 1];
            } else {
                i++;
            }
        }

        return kmp;
    }
}
