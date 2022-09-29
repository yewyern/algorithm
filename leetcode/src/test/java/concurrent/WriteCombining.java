package concurrent;

/**
 * 合并写
 *
 * @author xuzhou
 * @since 2021/4/25 9:46
 */
public class WriteCombining {

    public static final int ITERATIONS = Integer.MAX_VALUE;
    public static final int ITEMS = 1 << 24;
    public static final int MASK = ITEMS - 1;

    public static final byte[] arrayA = new byte[ITEMS];
    public static final byte[] arrayB = new byte[ITEMS];
    public static final byte[] arrayC = new byte[ITEMS];
    public static final byte[] arrayD = new byte[ITEMS];

    public static final byte[] arrayE = new byte[ITEMS];
    public static final byte[] arrayF = new byte[ITEMS];
    public static final byte[] arrayG = new byte[ITEMS];
    public static final byte[] arrayH = new byte[ITEMS];

    public static final byte[] arrayI = new byte[ITEMS];
    public static final byte[] arrayJ = new byte[ITEMS];
    public static final byte[] arrayK = new byte[ITEMS];
    public static final byte[] arrayL = new byte[ITEMS];

    public static final byte[] arrayM = new byte[ITEMS];
    public static final byte[] arrayN = new byte[ITEMS];
    public static final byte[] arrayO = new byte[ITEMS];
    public static final byte[] arrayP = new byte[ITEMS];

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            System.out.println("i " + i + " single loop durations (ns) = " + runCaseSingleLoop());
            System.out.println("i " + i + " split  loop durations (ns) = " + runCaseSplitLoop());
        }
    }

    public static long runCaseSingleLoop() {
        long start = System.nanoTime();
        int i = ITERATIONS;

        while (--i != 0) {
            int slot = i & MASK;
            byte b = (byte) i;
            arrayA[slot] = b;
            arrayB[slot] = b;
            arrayC[slot] = b;
            arrayD[slot] = b;

            arrayE[slot] = b;
            arrayF[slot] = b;
            arrayG[slot] = b;
//            arrayH[slot] = b;

            arrayI[slot] = b;
            arrayJ[slot] = b;
            arrayK[slot] = b;
            arrayL[slot] = b;

            arrayM[slot] = b;
            arrayN[slot] = b;
            arrayO[slot] = b;
//            arrayP[slot] = b;
        }
        return System.nanoTime() - start;
    }

    public static long runCaseSplitLoop() {
        long start = System.nanoTime();
        int i = ITERATIONS;

        while (--i != 0) {
            int slot = i & MASK;
            byte b = (byte) i;
            arrayA[slot] = b;
            arrayB[slot] = b;
            arrayC[slot] = b;
            arrayD[slot] = b;

            arrayE[slot] = b;
            arrayF[slot] = b;
            arrayG[slot] = b;
//            arrayH[slot] = b;
        }
        i = ITERATIONS;
        while (--i != 0) {
            int slot = i & MASK;
            byte b = (byte) i;
            arrayI[slot] = b;
            arrayJ[slot] = b;
            arrayK[slot] = b;
            arrayL[slot] = b;

            arrayM[slot] = b;
            arrayN[slot] = b;
            arrayO[slot] = b;
//            arrayP[slot] = b;
        }
        return System.nanoTime() - start;
    }

}
