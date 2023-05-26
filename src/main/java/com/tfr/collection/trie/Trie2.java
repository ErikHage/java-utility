package com.tfr.collection.trie;

import java.util.HashMap;
import java.util.Map;

public class Trie2 {

    private boolean isLeaf;
    private final Map<Character, Trie2> children;

    public Trie2() {
        isLeaf = false;
        children = new HashMap<>();
    }

    public boolean isEmpty() {
        return children.isEmpty();
    }

    public void insert(String word) {
        System.out.printf("Inserting %s%n", word);
        Trie2 current = this;

        for (char c: word.toCharArray()) {
            current.children.putIfAbsent(c, new Trie2());
            current = current.children.get(c);
        }

        current.isLeaf = true;
    }

    public boolean search(String word) {
        System.out.printf("Searching %s%n", word);
        Trie2 current = this;

        for (char c: word.toCharArray()) {
            current = current.children.get(c);

            if (current == null) {
                return false;
            }
        }

        return current.isLeaf;
    }

    public boolean delete(String word) {
        System.out.printf("Deleting %s%n", word);
        return delete(this, word, 0);
    }

    private boolean delete(Trie2 current, String word, int index) {
        if (index == word.length()) {
            if (!current.isLeaf) {
                return false;
            }
            current.isLeaf = false;
            return current.children.isEmpty();
        }

        char c = word.charAt(index);
        Trie2 child = current.children.get(c);
        if (child == null) {
            return false;
        }

        boolean shouldDeleteCurrentNode = delete(child, word, index + 1)
                && !child.isLeaf;

        if (shouldDeleteCurrentNode) {
            current.children.remove(c);
            return current.children.isEmpty();
        }

        return false;
    }
}
