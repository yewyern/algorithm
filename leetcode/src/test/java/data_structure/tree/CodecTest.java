package data_structure.tree;

import org.junit.Test;
import utils.TreeNode;

/**
 * @author xuzhou
 * @since 2023/7/13 14:36
 */
public class CodecTest {
    Codec codec = new Codec();

    @Test
    public void test() {
        check("[1,2,3,null,null,4,5,6,7]");
        check("[1,2,3,null,null,4,5]");
        check("[]");
    }

    private void check(String s) {
        TreeNode node = codec.deserialize(s);
        String serialize = codec.serialize(node);
        if (!s.equals(serialize)) {
            System.out.println("s = " + s);
            System.out.println("s2 = " + serialize);
        }
    }
}
