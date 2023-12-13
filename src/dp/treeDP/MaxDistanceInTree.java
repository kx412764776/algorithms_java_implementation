package dp.treeDP;

public class MaxDistanceInTree {

    public static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node(int value) {
            this.value = value;
        }
    }

    public static int maxDistance(Node head) {
        return process(head).maxDistance;
    }

    public static class Info{
        public int maxDistance;
        public int height;

        public Info(int distance, int h) {
            maxDistance = distance;
            height = h;
        }
    }

    /**
     * get the max distance and height of the tree
     * @param x the root of the tree
     * @return the max distance and height of the tree
     */
    public static Info process(Node x) {
        if (x == null) return new Info(0, 0);

        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);

        int p1 = leftInfo.maxDistance;
        int p2 = rightInfo.maxDistance;
        int p3 = leftInfo.height + 1 + rightInfo.height;
        int maxDistance = Math.max(p3, Math.max(p1, p2));
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        return new Info(maxDistance, height);
    }
}
