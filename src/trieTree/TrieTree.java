package trieTree;

public class TrieTree {

    public static class TrieNode {
        public int pass;
        public int end;
        // HashMap<Character, Node> nextNodes
        // TreeMap<Character, Node> nextNodes
        public TrieNode[] nextNodes;

        public TrieNode() {
            pass = 0;
            end = 0;
            // nextNodes[0] == null means there is no edge to 'a'
            // nextNodes[0] != null means there is an edge to 'a'
            // nextNodes[25] != null means there is an edge to 'z'
            nextNodes = new TrieNode[26];
        }
    }

    public static class Trie{
        private TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public void insert(String word) {

            if (word == null) return;

            char[] chars = word.toCharArray();
            TrieNode node = root;
            node.pass++;
            int index = 0;
            for (int i = 0; i < chars.length; i++) { // traverse each char from left to right
                index = chars[i] - 'a';
                if (node.nextNodes[index] == null) {
                    node.nextNodes[index] = new TrieNode();
                }
                node = node.nextNodes[index];
                node.pass++;
            }
            node.end++;
        }

        public void delete(String word) {
            if (search(word) != 0) {
                char[] chars = word.toCharArray();
                TrieNode node = root;
                node.pass--;
                int index = 0;
                for (int i = 0; i < chars.length; i++) {
                    index = chars[i] - 'a';
                    if (--node.nextNodes[index].pass == 0) {
                        node.nextNodes[index] = null;
                        return;
                    }
                    node = node.nextNodes[index];
                }
                node.end--;
            }
        }

        // return the times of the word that have been inserted into the trie tree
        public int search(String word) {
            if (word == null) return 0;

            char[] chars = word.toCharArray();
            TrieNode node = root;
            int index = 0;
            for (int i = 0; i < chars.length; i++) {
                index = chars[i] - 'a';
                if (node.nextNodes[index] == null){
                    return 0;
                }
                node = node.nextNodes[index];
            }
            return node.end;
        }

        // return the number of words that have the prefix in the trie tree
        public int prefixNumber(String prefix) {
            if (prefix == null) return 0;

            char[] chars = prefix.toCharArray();
            TrieNode node = root;
            int index = 0;
            for (int i = 0; i < chars.length; i++) {
                index = chars[i] - 'a';
                if (node.nextNodes[index] == null) {
                    return 0;
                }
                node = node.nextNodes[index];
            }
            return node.pass;
        }
    }
}
