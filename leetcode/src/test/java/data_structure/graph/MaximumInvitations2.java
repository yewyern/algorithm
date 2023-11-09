package data_structure.graph;


/**
 * <a href="https://leetcode.cn/problems/maximum-employees-to-be-invited-to-a-meeting">2127. 参加会议的最多员工数</a>
 * @author xuzhou
 * @since 2023/11/9 13:58
 */
public class MaximumInvitations2 {

    boolean[] visited;
    Circle[] circles;
    int size;
    int max;

    public int maximumInvitations(int[] favorite) {
        // 1、一定是要有环的，无环图，无法邀请任何一个人
        // 2、一个点可以有多个入度，但只有一个出度
        // 3、如果2个点成环，每个点可以额外邀请一个跟环联通的链
        // 4、如果是3个点及以上成环，不能再加额外的链
        // 5、3个点以上的环只能有一个，2个点的环可以有多个
        max = 0;
        int n = favorite.length;
        Node[] nodes = new Node[n];
        visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(i);
        }
        for (int i = 0; i < n; i++) {
            nodes[i].favorite = nodes[favorite[i]];
        }
        circles = new Circle[n >> 1];
        for (Node node : nodes) {
            populate(node);
        }
        int twoNodeCount = 0;
        for (int i = 0; i < size; i++) {
            Circle circle = circles[i];
            twoNodeCount += circle.count + circle.leftLinkCount + circle.rightLinkCount;
        }
        return Math.max(max, twoNodeCount);
    }

    private void populate(Node node) {
        if (visited[node.index]) {
            return;
        }
        // 1、通过双指针找到链表入环节点
        Node inCircleNode = findInCircleNode(node);
        if (!visited[inCircleNode.index]) {
            // 2、环上的点没访问过，给环上的点打标计算
            Circle circle = new Circle();
            int count = 0;
            Node curr = inCircleNode;
            do {
                visited[curr.index] = true;
                curr.circle = circle;
                count++;
                curr = curr.favorite;
            } while (curr != inCircleNode);
            circle.count = count;
            if (count == 2) {
                circle.left = inCircleNode;
                circle.right = inCircleNode.favorite;
                circles[size++] = circle;
            } else {
                max = Math.max(max, count);
            }
        }
        // 环已经计算过，找到第一个已经访问过的节点，递归计算
        dfs(node, inCircleNode);
        if (node.inCircleNode == node.circle.left) {
            node.circle.leftLinkCount = Math.max(node.circle.leftLinkCount, node.count);
        } else if (node.inCircleNode == node.circle.right) {
            node.circle.rightLinkCount = Math.max(node.circle.rightLinkCount, node.count);
        }
    }

    private void dfs(Node node, Node inCircleNode) {
        if (visited[node.index]) {
            return;
        }
        Node favorite = node.favorite;
        dfs(favorite, inCircleNode);
        node.circle = favorite.circle;
        node.inCircleNode = favorite.inCircleNode == null ? inCircleNode : favorite.inCircleNode;
        node.count = favorite.count + 1;
        visited[node.index] = true;
    }

    private Node findInCircleNode(Node head) {
        Node slow = head.favorite;
        Node fast = head.favorite.favorite;
        while (!visited[fast.index] && slow != fast) {
            slow = slow.favorite;
            fast = fast.favorite.favorite;
        }
        if (visited[fast.index]) {
            while (!visited[slow.index]) {
                slow = slow.favorite;
            }
            return slow;
        }
        fast = head;
        while (fast != slow) {
            fast = fast.favorite;
            slow = slow.favorite;
        }
        return slow;
    }

    private static class Node {
        int index; // 环
        Circle circle; // 环
        Node favorite; // 连接的下一个节点
        Node inCircleNode; // 非环节点连接到环的节点，当2个节点的环时需要用到
        int count; // 从入环的起点到当前节点的总数

        public Node(int index) {
            this.index = index;
        }
    }

    private static class Circle {
        int count;// 环上的节点数
        Node left; // 2个点的环左节点
        Node right; // 2个点的环右节点
        int leftLinkCount; // 2个点的环左节点外链最大长度
        int rightLinkCount; // 2个点的环右节点外链最大长度
    }

}