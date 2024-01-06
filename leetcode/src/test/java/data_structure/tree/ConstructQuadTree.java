package data_structure.tree;

/**
 * <a href="https://leetcode.cn/problems/construct-quad-tree/">427. 建立四叉树</a>
 * @author xuzhou
 * @since 2024/1/6 21:27
 */
public class ConstructQuadTree {

    public Node construct(int[][] grid) {
        int n = grid.length;
        return partition(grid, 0, 0, n);
    }

    private Node partition(int[][] grid, int r0, int c0, int n) {
        if (n == 1) {
            return new Node(grid[r0][c0] == 1, true);
        }
        n >>= 1;
        return merge(
                partition(grid, r0, c0, n),
                partition(grid, r0, c0 + n, n),
                partition(grid, r0 + n, c0, n),
                partition(grid, r0 + n, c0 + n, n)
        );
    }

    private Node merge(Node topLeft,
                       Node topRight,
                       Node bottomLeft,
                       Node bottomRight) {
        if (topLeft.isLeaf && topRight.isLeaf && bottomLeft.isLeaf && bottomRight.isLeaf) {
            boolean and = topLeft.val & topRight.val & bottomLeft.val & bottomRight.val;
            if (and) {
                return new Node(true, true);
            }
            boolean or = topLeft.val | topRight.val | bottomLeft.val | bottomRight.val;
            if (!or) {
                return new Node(false, true);
            }
        }
        return new Node(topLeft.val, false, topLeft, topRight, bottomLeft, bottomRight);
    }

    private class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;


        public Node() {
            this.val = false;
            this.isLeaf = false;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = topLeft;
            this.topRight = topRight;
            this.bottomLeft = bottomLeft;
            this.bottomRight = bottomRight;
        }
    }
}

