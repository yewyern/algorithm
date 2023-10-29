package union_find_set;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhou.xu
 * @since 2023/10/29 21:58
 */
public class UnionFindSet<V> {

    private Map<V, Node<V>> nodes;
    private Map<Node<V>, Node<V>> parentMap;
    private Map<Node<V>, Integer> sizeMap;

    public UnionFindSet(List<V> list) {
        this.nodes = new HashMap<>();
        this.parentMap = new HashMap<>();
        this.sizeMap = new HashMap<>();
        for (V v : list) {
            Node<V> vNode = new Node<>(v);
            nodes.put(v, vNode);
            parentMap.put(vNode, vNode);
            sizeMap.put(vNode, 1);
        }
    }

    private Node<V> findFather(Node<V> cur) {
        if (parentMap.get(cur) == cur) {
            return cur;
        }
        Node<V> father = findFather(parentMap.get(cur));
        parentMap.put(cur, father);
        return father;
    }

    public boolean isSameSet(V a, V b) {
        return findFather(nodes.get(a)) == findFather(nodes.get(b));
    }

    public void union(V a, V b) {
        Node<V> fatherA = findFather(nodes.get(a));
        Node<V> fatherB = findFather(nodes.get(b));
        if (fatherA != fatherB) {
            Integer aSize = sizeMap.get(fatherA);
            Integer bSize = sizeMap.get(fatherB);
            if (aSize >= bSize) {
                parentMap.put(fatherB, fatherA);
                sizeMap.put(fatherA, aSize + bSize);
                sizeMap.remove(fatherB);
            } else {
                parentMap.put(fatherA, fatherB);
                sizeMap.put(fatherB, aSize + bSize);
                sizeMap.remove(fatherA);
            }
        }
    }

    private static class Node<V> {
        V val;

        public Node(V val) {
            this.val = val;
        }
    }
}
