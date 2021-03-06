package com.audi.trie;

import java.util.HashMap;

/**
 * Trie
 * <p>
 * https://leetcode.com/problems/implement-trie-prefix-tree/
 *
 * @author WangQuanzhou
 * @date 2020-02-15
 */
public class Trie208 {

    private class Node {

        /**
         * 当前节点是否是完整的单词
         */
        public boolean isWord;

        /**
         * 当前节点的后继节点，因为不确定后继节点个数，这里使用map存储
         */
        public HashMap<Character, Node> next;

        public Node(boolean isWord) {
            this.isWord = isWord;
            next = new HashMap<>();
        }

        public Node() {
            this(false);
        }
    }

    private Node root;
    private int size;

    public Trie208() {
        root = new Node();
        size = 0;
    }

    // 获得Trie中存储的单词数量
    public int getSize() {
        return size;
    }

    // 向Trie中添加一个新的单词word
    public void insert(String word) {

        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null)
                cur.next.put(c, new Node());
            cur = cur.next.get(c);
        }

        // 避免重复标记单词，首先判断一下当前位置是否已是一个完整的单词
        if (!cur.isWord) {
            cur.isWord = true;
            size++;
        }
    }

    // 查询单词word是否在Trie中
    public boolean search(String word) {

        Trie208.Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null)
                return false;
            cur = cur.next.get(c);
        }
        return cur.isWord;
    }

    // 查询是否在Trie中有单词以prefix为前缀
    public boolean startsWith(String prefix) {

        Trie208.Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (cur.next.get(c) == null)
                return false;
            cur = cur.next.get(c);
        }

        return true;
    }
}
