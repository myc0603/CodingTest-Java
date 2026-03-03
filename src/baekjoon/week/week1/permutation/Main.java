package baekjoon.week.week1.permutation;

import java.util.Arrays;

public class Main {

    static int[] arr = {1, 2, 3, 4, 5, 6};
    static int n = arr.length;
    static int r = 3;

    static int[] result = new int[r];
    static boolean[] visited = new boolean[n];

    static int cnt = 0;

    public static void main(String[] args) {
        perm(0);
    }

    private static void perm(int depth) {
        if (depth == r) {
            System.out.println(++cnt + " - " + Arrays.toString(result));
            return;
        }

        for (int i = 0; i < n; ++i) {
            if (visited[i]) continue;

            visited[i] = true;
            result[depth] = arr[i];
            perm(depth + 1);
            visited[i] = false;
        }
    }

}
