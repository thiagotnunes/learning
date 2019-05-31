package com.learning.algorithms.strings.matcher;

import com.learning.datastructures.trie.Trie;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TrieMatcher implements Matcher {
    /**
     * Time complexity - O(n^2 + m)
     * Space complexity - O(n^2)
     */
    @Override
    public List<Integer> match(String text, String pattern) {
        int n = Optional.ofNullable(text).map(String::length).orElse(0);
        int m = Optional.ofNullable(pattern).map(String::length).orElse(0);

        if (m > n) return new ArrayList<>();
        if (n == 0) return new ArrayList<>();
        if (m == 0) return new ArrayList<>();

        Trie.Node root = Trie.from(text);
        for (int i = 1; i < n; i++) {
            Trie.append(root, text.substring(i, n), i);
        }

        Trie.Node current = root;
        for (int i = 0; i < m; i++) {
            Character c = pattern.charAt(i);
            current = current.nextNode(c);
        }

        return getPositionsFrom(current);
    }

    private List<Integer> getPositionsFrom(Trie.Node node) {
        ArrayList<Integer> positions = new ArrayList<>();

        getPositionsFrom(node, positions);

        return positions;
    }

    private void getPositionsFrom(Trie.Node node, List<Integer> positions) {
        if (node == null) return;

        if (node.isTerminating()) {
            positions.add(node.getPosition());
        }
        for (Trie.Node next : node.nextNodes()) {
            getPositionsFrom(next, positions);
        }
    }
}
