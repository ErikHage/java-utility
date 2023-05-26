package com.tfr.collection.trie;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Trie2Test {

    @Test
    public void testEmpty_GivenNoElements_ExpectTrue() {
        Trie2 trie = new Trie2();

        assertTrue(trie.isEmpty());
    }

    @Test
    public void testEmpty_GivenElements_ExpectFalse() {
        Trie2 trie = createTestTrie2();

        assertFalse(trie.isEmpty());
    }

    @Test
    public void testSearch_GivenWordsAdded_GivenWordInTrie2_ExpectTrue() {
        Trie2 trie = createTestTrie2();

        assertTrue(trie.search("programming"));
        assertTrue(trie.search("is"));
        assertTrue(trie.search("a"));
        assertTrue(trie.search("way"));
        assertTrue(trie.search("of"));
        assertTrue(trie.search("life"));
    }

    @Test
    public void testSearch_GivenWordsAdded_GivenWordNotInTrie2_ExpectFalse() {
        Trie2 trie = createTestTrie2();

        assertFalse(trie.search("3"));
        assertFalse(trie.search("vida"));
    }

    @Test
    public void testDelete_GivenWordNotInTrie2_ExpectFalse() {
        Trie2 trie = new Trie2();

        assertFalse(trie.delete("someword"));
    }

    @Test
    public void testDelete_GivenWordInTrie2_ExpectRemoved() {
        Trie2 trie = createTestTrie2();

        assertTrue(trie.search("programming"));
        trie.delete("programming");
        assertFalse(trie.search("programming"));
    }

    @Test
    public void testDelete_GivenTrie2WithOverlappingWords_ExpectDontDeleteSubElement() {
        Trie2 trie1 = new Trie2();

        trie1.insert("pie");
        trie1.insert("pies");

        trie1.delete("pies");

        assertTrue(trie1.search("pie"));
    }

    @Test
    public void testDelete_GivenTrie2WithOverlappingWords_ExpectDeleteSubElement() {
        Trie2 trie1 = new Trie2();

        trie1.insert("pie");
        trie1.insert("pies");

        trie1.delete("pie");

        assertTrue(trie1.search("pies"));
    }

    @Test
    public void testDelete_GivenWordNotInTrie2_ExpectNotDeleted() {
        Trie2 trie1 = new Trie2();

        trie1.insert("pies");
        trie1.delete("pie");

        assertTrue(trie1.search("pies"));
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
