package com.tfr.collection.trie;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TrieTest {

    @Test
    public void testEmpty_GivenNoElements_ExpectTrue() {
        Trie trie = new Trie();

        assertTrue(trie.isEmpty());
    }

    @Test
    public void testEmpty_GivenElements_ExpectFalse() {
        Trie trie = createTestTrie();

        assertFalse(trie.isEmpty());
    }

    @Test
    public void testContainsNode_GivenWordsAdded_GivenWordInTrie_ExpectTrue() {
        Trie trie = createTestTrie();

        assertTrue(trie.containsNode("programming"));
        assertTrue(trie.containsNode("is"));
        assertTrue(trie.containsNode("a"));
        assertTrue(trie.containsNode("way"));
        assertTrue(trie.containsNode("of"));
        assertTrue(trie.containsNode("life"));
    }

    @Test
    public void testContainsNode_GivenWordsAdded_GivenWordNotInTrie_ExpectFalse() {
        Trie trie = createTestTrie();

        assertFalse(trie.containsNode("3"));
        assertFalse(trie.containsNode("vida"));
    }

    @Test
    public void testDelete_GivenWordNotInTrie_ExpectFalse() {
        Trie trie = new Trie();

        assertFalse(trie.delete("someword"));
    }

    @Test
    public void testDelete_GivenWordInTrie_ExpectRemoved() {
        Trie trie = createTestTrie();

        assertTrue(trie.containsNode("programming"));
        trie.delete("programming");
        assertFalse(trie.containsNode("programming"));
    }

    @Test
    public void testDelete_GivenTrieWithOverlappingWords_ExpectDontDeleteSubElement() {
        Trie trie1 = new Trie();

        trie1.insert("pie");
        trie1.insert("pies");

        trie1.delete("pies");

        assertTrue(trie1.containsNode("pie"));
    }

    private Trie createTestTrie() {
        Trie trie = new Trie();

        trie.insert("programming");
        trie.insert("is");
        trie.insert("a");
        trie.insert("way");
        trie.insert("of");
        trie.insert("life");

        return trie;
    }
}
