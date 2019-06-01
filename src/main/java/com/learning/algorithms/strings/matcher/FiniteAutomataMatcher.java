package com.learning.algorithms.strings.matcher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class FiniteAutomataMatcher implements Matcher {

    /**
     * k - size of the alphabet
     * Time complexity - O(m^2 * k + n)
     * Space complexity - O(m * k + m)
     */
    @Override
    public List<Integer> match(String text, String pattern) {
        int n = Optional.ofNullable(text).map(String::length).orElse(0);
        int m = Optional.ofNullable(pattern).map(String::length).orElse(0);
        List<Integer> result = new ArrayList<>();

        Map<Character, Integer> characterToIndex = new HashMap<>();
        Map<Integer, Character> indexToCharacter = new HashMap<>();
        int count = 0;
        // O(m)
        for (int i = 0; i < m; i++) {
            if (!characterToIndex.containsKey(pattern.charAt(i))) {
                characterToIndex.put(pattern.charAt(i), count);
                indexToCharacter.put(count, pattern.charAt(i));
                count++;
            }
        }

        int[][] finiteAutomata = finiteAutomataFor(pattern, m, characterToIndex, indexToCharacter);

        int current = 0;
        // O(n)
        for (int i = 0; i < n; i++) {
            Character c = text.charAt(i);
            Integer index = characterToIndex.get(c);

            if (index == null) {
                current = 0;
            } else {
                current = finiteAutomata[current][index];

                if (current == finiteAutomata.length - 1) {
                    result.add(i - m + 1);
                }

            }
        }

        return result;
    }

    private int[][] finiteAutomataFor(String s,
                                      int m,
                                      Map<Character, Integer> characterToIndex,
                                      Map<Integer, Character> indexToCharacter) {

        int k = characterToIndex.size();
        int[][] finiteAutomata = new int[m + 1][k];

        // O(m)
        for (int i = 0; i < m + 1; i++) {
            Arrays.fill(finiteAutomata[i], -1);
        }
        // O(m)
        for (int i = 0; i < m; i++) {
            char c = s.charAt(i);
            Integer j = characterToIndex.get(c);
            finiteAutomata[i][j] = i + 1;
        }
        // O(k)
        for (int i = 0; i < k; i++) {
            if (finiteAutomata[0][i] < 0) {
                finiteAutomata[0][i] = 0;
            }
        }
        // O(m^2 * k)
        for (int i = 1; i < m + 1; i++) {
            for (int j = 0; j < k; j++) {
                if (finiteAutomata[i][j] < 0) {
                    String s1 = s.substring(0, i);
                    // O(m)
                    String s2 = s.substring(1, i) + indexToCharacter.get(j);
                    // O(m)
                    while (!s1.equals(s2) && s1.length() > 0) {
                        s1 = s1.substring(0, s1.length() - 1);
                        s2 = s2.substring(1);
                    }
                    finiteAutomata[i][j] = s1.length();
                }
            }
        }

        return finiteAutomata;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
