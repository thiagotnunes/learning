package com.learning.algorithms.strings.matcher;

import com.learning.datastructures.trie.Trie;

import java.util.ArrayList;
import java.util.List;

public class TrieMatcher {
    /**
     * Time complexity - O(n^2 + m)
     * Space complexity - O(n^2)
     */
    public List<Integer> match(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();

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
