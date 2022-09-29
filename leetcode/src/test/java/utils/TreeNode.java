package utils;

/**
 * @author xzer
 * @since
 */
public class TreeNode {

    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
    }
    
    public TreeNode(int x) {
        val = x;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "{" +
            "\"val\":" + val +
            ", \"left\":" + left +
            ", \"right\":" + right +
            '}';
    }
}