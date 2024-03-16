package concurrent;

import java.lang.instrument.Instrumentation;

/**
 * @author xuzhou
 * @since 2021/4/27 10:32
 */
public class ObjectSizeAgent {

    private static Instrumentation instrumentation;

    public static void premain(String agentArgs, Instrumentation _inst) {
        instrumentation = _inst;
    }

    public static long sizeof(Object o) {
        return instrumentation.getObjectSize(o);
    }

}
