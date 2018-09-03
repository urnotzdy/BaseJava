public class Main {

    public static void main(String[] args) {
          final int COUNT_BITS = Integer.SIZE - 3;
        // runState is stored in the high-order bits
          final int RUNNING    = 1 << COUNT_BITS;
        System.out.println(COUNT_BITS);
        System.out.println(RUNNING);
    }
}
