package normal;

import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import org.junit.Test;

/**
 * DAG示意图
 * <p> 例1：
 * <p> a + b + c
 * <p>     +
 * <p>    / \
 * <p>   +   c
 * <p>  / \
 * <p> a   b
 *
 * @author zhou.xu
 * @since 2020/8/28 9:09
 */
public class DagTest {

    private static final Set<Character> OPERATOR_SET = Sets.newHashSet('+', '*', '(', ')');

    @Test
    public void test() {
        transformDag("a + b + c");
        transformDag("a + c + b + a + c");
        transformDag("(a + b) * c * (a + c) + b");
    }

    public void transformDag(String s) {
        List<String> identifiers = new ArrayList<>();
        char[] cs = s.toCharArray();
        boolean isEnd;
        StringBuilder v = new StringBuilder();
        for (int i = 0; i < cs.length; i++) {
            if (' ' == cs[i] || OPERATOR_SET.contains(cs[i])) {
                isEnd = true;
            } else {
                v.append(cs[i]);
                isEnd = i == cs.length - 1;
            }

            if (isEnd && v.length() > 0) {
                identifiers.add(v.toString());
                v = new StringBuilder();
            }
            if (OPERATOR_SET.contains(cs[i])) {
                identifiers.add(String.valueOf(cs[i]));
            }
        }
        System.out.println("identifiers = " + identifiers);
    }
}

class DagNode {

    final String value;
    final boolean isOperator;
    DagNode left;
    DagNode right;

    public DagNode(String value, boolean isOperator) {
        this.value = value;
        this.isOperator = isOperator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DagNode dagNode = (DagNode) o;
        return isOperator == dagNode.isOperator &&
            value.equals(dagNode.value) &&
            left.equals(dagNode.left) &&
            right.equals(dagNode.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, isOperator, left, right);
    }
}
