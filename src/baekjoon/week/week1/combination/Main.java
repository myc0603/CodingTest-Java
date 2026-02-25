package baekjoon.week.week1.combination;

import java.util.Arrays;

public class Main {

    static int[] arr = {1, 2, 3, 4, 5};
    static int N = arr.length;
    static int R = 2;

    static int[] result = new int[R];
    static int cnt = 0;

    public static void comb(int depth, int start) {
        if (depth == R) {
            System.out.println(++cnt + " - " + Arrays.toString(result));
            return;
        }

        for (int i = start; i < N; ++i) {
            result[depth] = arr[i];
            comb(depth + 1, i + 1);
        }
    }

    public static void main(String[] args) {
        comb(0, 0);
    }
}
