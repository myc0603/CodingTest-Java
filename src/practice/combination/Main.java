package practice.combination;

import java.util.Arrays;

public class Main {

    static int[] arr = {1, 2, 3, 4, 5};
    static int n = arr.length;
    static int r = 3;

    static int[] result = new int[r];

    static int cnt = 0;

    public static void main(String[] args) {
        comb(0, 0);
    }

    private static void comb(int depth, int start) {
        if (depth == r) {
            System.out.println(++cnt + " - " + Arrays.toString(result));
            return;
        }

        for (int i = start; i < n; ++i) {
            result[depth] = arr[i];
            comb(depth + 1, i + 1);
        }
    }

}
