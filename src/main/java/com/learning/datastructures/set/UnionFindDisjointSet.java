package com.learning.datastructures.set;

public class UnionFindDisjointSet {
    private final int capacity;
    private final int[] parents;
    private final int[] ranks;

    public UnionFindDisjointSet(int capacity) {
        this.capacity = capacity;
        this.parents = new int[capacity];
        this.ranks = new int[capacity];

        for (int i = 0; i < capacity; i++) {
            parents[i] = i;
            ranks[i] = 1;
        }
    }

    /**
     * Time complexity - O(1) - amortized
     * Space complexity - O(1) - amortized
     */
    public int find(int e) {
        if (e > capacity) throw new RuntimeException("can not find " + e + " (capacity " + capacity + ")");
        if (parents[e] == e) return e;

        int parent = find(parents[e]);
        parents[e] = parent;
        return parent;
    }

    /**
     * Time complexity - O(1) - amortized
     * Space complexity - O(1) - amortized
     */
    public void union(int e1, int e2) {
        int parent1 = find(e1);
        int parent2 = find(e2);

        if (ranks[parent1] >= ranks[parent2]) {
            parents[e2] = parent1;
            ranks[parent1]++;
            ranks[parent2] = 0;
        } else {
            parents[e1] = parent2;
            ranks[parent1] = 0;
            ranks[parent2]++;
        }
    }
}
