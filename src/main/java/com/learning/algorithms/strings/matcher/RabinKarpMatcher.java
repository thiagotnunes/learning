package com.learning.algorithms.strings.matcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RabinKarpMatcher implements Matcher {
    public static final int PRIME = 101;

    /**
     * Time complexity - O(m * n), but better on average O(m + n)
     * Space complexity - O(1)
     */
    @Override
    public List<Integer> match(String text, String pattern) {
        int n = Optional.ofNullable(text).map(String::length).orElse(0);
        int m = Optional.ofNullable(pattern).map(String::length).orElse(0);
        List<Integer> result = new ArrayList<>();

        if (m > n) return result;
        if (n == 0) return result;
        if (m == 0) return result;

        int patternHash = calculateHash(pattern, m);
        int textHash = calculateHash(text, m);

        int i = 0;
        while  (i < n - m + 1) {
            if (textHash == patternHash && equals(text, i, i + m - 1, pattern)) {
                result.add(i);
            }

            i++;
            if (i < n - m + 1) {
                textHash -= text.charAt(i - 1);
                textHash /= PRIME;
                textHash += text.charAt(i + m - 1) * ((int) Math.pow(PRIME, m - 1));
            }
        }

        return result;
    }

    private int calculateHash(String s, int size) {
        int hash = 0;
        for (int i = 0; i < size; i++) {
            hash += s.charAt(i) * ((int) Math.pow(PRIME, i));
        }
        return hash;
    }

    private boolean equals(String text,
                           int textStart,
                           int textEnd,
                           String pattern) {
        for (int i = 0; i < textEnd - textStart + 1; i++) {
            if (text.charAt(textStart + i) != pattern.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
