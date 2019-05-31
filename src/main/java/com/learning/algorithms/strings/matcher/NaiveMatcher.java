package com.learning.algorithms.strings.matcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NaiveMatcher implements Matcher {
    /**
     * Time complexity - O(m * n)
     * Space complexity - O(1)
     */
    @Override
    public List<Integer> match(String text, String pattern) {
        int n = Optional.ofNullable(text).map(String::length).orElse(0);
        int m = Optional.ofNullable(pattern).map(String::length).orElse(0);
        List<Integer> matches = new ArrayList<>();

        if (m > n) return matches;
        if (n == 0) return matches;
        if (m == 0) return matches;

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
