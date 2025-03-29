package patterns.tries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TriePattern {
    /*
     * This pattern is used in NeetCode 150 problems like:
     * 1. Implement Trie (Prefix Tree)
     * 2. Design Add and Search Words Data Structure
     * 3. Word Search II
     */
    
    static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        boolean isEndOfWord;
        String word; // Used for Word Search II
    }

    // Example 1: Implement Trie (LeetCode 208)
    static class Trie {
        private final TrieNode root;
        
        public Trie() {
            root = new TrieNode();
        }
        
        public void insert(String word) {
            TrieNode current = root;
            for (char ch : word.toCharArray()) {
                current.children.putIfAbsent(ch, new TrieNode());
                current = current.children.get(ch);
            }
            current.isEndOfWord = true;
        }
        
        public boolean search(String word) {
            TrieNode node = searchNode(word);
            return node != null && node.isEndOfWord;
        }
        
        public boolean startsWith(String prefix) {
            return searchNode(prefix) != null;
        }
        
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
    }
    
    // Example 2: Design Add and Search Words Data Structure (LeetCode 211)
    static class WordDictionary {
        private final TrieNode root;
        
        public WordDictionary() {
            root = new TrieNode();
        }
        
        public void addWord(String word) {
            TrieNode current = root;
            for (char ch : word.toCharArray()) {
                current.children.putIfAbsent(ch, new TrieNode());
                current = current.children.get(ch);
            }
            current.isEndOfWord = true;
        }
        
        public boolean search(String word) {
            return searchHelper(word, 0, root);
        }
        
        private boolean searchHelper(String word, int index, TrieNode node) {
            if (index == word.length()) {
                return node.isEndOfWord;
            }
            
            char ch = word.charAt(index);
            if (ch == '.') {
                for (TrieNode child : node.children.values()) {
                    if (searchHelper(word, index + 1, child)) {
                        return true;
                    }
                }
                return false;
            } else {
                if (!node.children.containsKey(ch)) {
                    return false;
                }
                return searchHelper(word, index + 1, node.children.get(ch));
            }
        }
    }
    
    // Example 3: Word Search II (LeetCode 212)
    public static List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        TrieNode root = buildTrie(words);
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, i, j, root, result);
            }
        }
        
        return result;
    }
    
    private static TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode current = root;
            for (char ch : word.toCharArray()) {
                current.children.putIfAbsent(ch, new TrieNode());
                current = current.children.get(ch);
            }
            current.isEndOfWord = true;
            current.word = word;
        }
        return root;
    }
    
    private static void dfs(char[][] board, int i, int j, TrieNode node, List<String> result) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || 
            board[i][j] == '#' || !node.children.containsKey(board[i][j])) {
            return;
        }
        
        char ch = board[i][j];
        node = node.children.get(ch);
        
        if (node.word != null) {
            result.add(node.word);
            node.word = null;  // avoid duplicates
        }
        
        board[i][j] = '#';
        dfs(board, i + 1, j, node, result);
        dfs(board, i - 1, j, node, result);
        dfs(board, i, j + 1, node, result);
        dfs(board, i, j - 1, node, result);
        board[i][j] = ch;
    }
    
    public static void main(String[] args) {
        // Test Trie
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println("Search 'apple': " + trie.search("apple"));
        System.out.println("Search 'app': " + trie.search("app"));
        System.out.println("Starts with 'app': " + trie.startsWith("app"));
        
        // Test WordDictionary
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");
        System.out.println("Search 'pad': " + wordDictionary.search("pad"));
        System.out.println("Search 'bad': " + wordDictionary.search("bad"));
        System.out.println("Search '.ad': " + wordDictionary.search(".ad"));
        
        // Test Word Search II
        char[][] board = {
            {'o','a','a','n'},
            {'e','t','a','e'},
            {'i','h','k','r'},
            {'i','f','l','v'}
        };
        String[] words = {"oath","pea","eat","rain"};
        System.out.println("Found words: " + findWords(board, words));
    }
}