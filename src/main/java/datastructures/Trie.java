package datastructures;

import java.util.*;

public class Trie {
    private TrieNode root;
    
    static class TrieNode {
        private final Map<Character, TrieNode> children;
        private boolean isEndOfWord;
        private String word; // Store complete word at end nodes
        
        TrieNode() {
            children = new HashMap<>();
            isEndOfWord = false;
            word = null;
        }
    }
    
    public Trie() {
        root = new TrieNode();
    }
    
    // Insert a word into the trie
    public void insert(String word) {
        TrieNode current = root;
        
        for (char ch : word.toCharArray()) {
            current.children.putIfAbsent(ch, new TrieNode());
            current = current.children.get(ch);
        }
        
        current.isEndOfWord = true;
        current.word = word;
    }
    
    // Search for a word in the trie
    public boolean search(String word) {
        TrieNode node = searchNode(word);
        return node != null && node.isEndOfWord;
    }
    
    // Search for a prefix in the trie
    public boolean startsWith(String prefix) {
        return searchNode(prefix) != null;
    }
    
    // Get all words with a given prefix
    public List<String> getAllWordsWithPrefix(String prefix) {
        List<String> result = new ArrayList<>();
        TrieNode node = searchNode(prefix);
        
        if (node != null) {
            collectWords(node, result);
        }
        
        return result;
    }
    
    // Helper method to search for a node
    private TrieNode searchNode(String str) {
        TrieNode current = root;
        
        for (char ch : str.toCharArray()) {
            if (!current.children.containsKey(ch)) {
                return null;
            }
            current = current.children.get(ch);
        }
        
        return current;
    }
    
    // Helper method to collect all words under a node
    private void collectWords(TrieNode node, List<String> result) {
        if (node.isEndOfWord) {
            result.add(node.word);
        }
        
        for (TrieNode child : node.children.values()) {
            collectWords(child, result);
        }
    }
    
    public static void main(String[] args) {
        Trie trie = new Trie();
        
        // Insert some words
        String[] words = {"apple", "app", "apricot", "bear", "beer"};
        for (String word : words) {
            trie.insert(word);
        }
        
        // Demonstrate search
        System.out.println("Search 'apple': " + trie.search("apple")); // true
        System.out.println("Search 'app': " + trie.search("app")); // true
        System.out.println("Search 'appl': " + trie.search("appl")); // false
        
        // Demonstrate prefix search
        System.out.println("Starts with 'app': " + trie.startsWith("app")); // true
        System.out.println("Starts with 'bet': " + trie.startsWith("bet")); // false
        
        // Demonstrate getting all words with prefix
        System.out.println("Words with prefix 'ap': " + trie.getAllWordsWithPrefix("ap")); // [apple, app, apricot]
        System.out.println("Words with prefix 'be': " + trie.getAllWordsWithPrefix("be")); // [bear, beer]
    }
}
