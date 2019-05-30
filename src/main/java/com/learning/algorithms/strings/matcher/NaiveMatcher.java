package com.learning.algorithms.strings.matcher;

import java.util.ArrayList;
import java.util.List;

public class NaiveMatcher {
    /**
     * Time complexity - O(m * n)
     * Space complexity - O(1)
     */
    public List<Integer> match(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();
        List<Integer> matches = new ArrayList<>();

        for (int i = 0; i < n - m + 1; i++) {
            boolean matched = true;
            for (int j = 0; j < m; j++) {
                if (text.charAt(i + j) != pattern.charAt(j)) {
                    matched = false;
                    break;
                }
            }
            if (matched) {
                matches.add(i);
            }
        }

        return matches;
    }
}
