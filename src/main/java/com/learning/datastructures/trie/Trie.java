package com.learning.datastructures.trie;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Trie {
    public static class Node {
        private Character value;
        private Map<Character, Node> next;
        private boolean terminating;
        private int position;

        public Node(Character value) {
            this.value = value;
            this.next = new HashMap<>();
        }

        public Character getValue() {
            return value;
        }

        public boolean isTerminating() {
            return terminating;
        }

        public int getPosition() {
            return position;
        }

        public void asTerminating(int position) {
            this.terminating = true;
            this.position = position;
        }

        public Node nextNode(Character c) {
            return next.get(c);
        }

        public void addNext(Character c) {
            next.put(c, new Node(c));
        }

        public Collection<Node> nextNodes() {
            return next.values();
        }
    }

    public static Node from(String s) {
        Node root = new Node(null);

        Node current = root;
        for (Character c : s.toCharArray()) {
            current.addNext(c);
            current = current.nextNode(c);
        }
        current.asTerminating(0);

        return root;
    }

    public static Node append(Node root, String s, int position) {
        Node current = root;
        for (Character c : s.toCharArray()) {
            Node nextNode = current.nextNode(c);
            if (nextNode != null) {
                current = nextNode;
            } else {
                current.addNext(c);
                current = current.nextNode(c);
            }
        }
        current.asTerminating(position);
        return root;
    }
}
