package com.tfr.collection.trie;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Trie2Test {

    @Test
    public void testEmpty_GivenNoElements_ExpectTrue() {
        Trie2 Trie2 = new Trie2();

        assertTrue(Trie2.isEmpty());
    }

    @Test
    public void testEmpty_GivenElements_ExpectFalse() {
        Trie2 Trie2 = createTestTrie2();

        assertFalse(Trie2.isEmpty());
    }

    @Test
    public void testSearch_GivenWordsAdded_GivenWordInTrie2_ExpectTrue() {
        Trie2 Trie2 = createTestTrie2();

        assertTrue(Trie2.search("programming"));
        assertTrue(Trie2.search("is"));
        assertTrue(Trie2.search("a"));
        assertTrue(Trie2.search("way"));
        assertTrue(Trie2.search("of"));
        assertTrue(Trie2.search("life"));
    }

    @Test
    public void testSearch_GivenWordsAdded_GivenWordNotInTrie2_ExpectFalse() {
        Trie2 Trie2 = createTestTrie2();

        assertFalse(Trie2.search("3"));
        assertFalse(Trie2.search("vida"));
    }

    @Test
    public void testDelete_GivenWordNotInTrie2_ExpectFalse() {
        Trie2 Trie2 = new Trie2();

        assertFalse(Trie2.delete("someword"));
    }

    @Test
    public void testDelete_GivenWordInTrie2_ExpectRemoved() {
        Trie2 Trie2 = createTestTrie2();

        assertTrue(Trie2.search("programming"));
        Trie2.delete("programming");
        assertFalse(Trie2.search("programming"));
    }

    @Test
    public void testDelete_GivenTrie2WithOverlappingWords_ExpectDontDeleteSubElement() {
        Trie2 Trie21 = new Trie2();

        Trie21.insert("pie");
        Trie21.insert("pies");

        Trie21.delete("pies");

        assertTrue(Trie21.search("pie"));
    }

    private Trie2 createTestTrie2() {
        Trie2 Trie2 = new Trie2();

        Trie2.insert("programming");
        Trie2.insert("is");
        Trie2.insert("a");
        Trie2.insert("way");
        Trie2.insert("of");
        Trie2.insert("life");

        return Trie2;
    }
}
