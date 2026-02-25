package baekjoon.week.week1.permutation;

import java.util.Arrays;


public class Main {

    static int[] arr = {1, 2, 3, 4, 5};
    static int N = arr.length;
    static int R = 3;
    static boolean[] visited = new boolean[N];
    static int[] result = new int[R];
    static int cnt = 0;

    static void perm(int depth) {
        if (depth == R) {
            System.out.println(++cnt + " - " + Arrays.toString(result));
            return;
        }

        for (int i = 0; i < N; ++i) {
            if (visited[i]) continue;

            visited[i] = true;
            result[depth] = arr[i];
            perm(depth + 1);
            visited[i] = false;
        }
    }

    public static void main(String[] args) {
        perm(0);
    }
}
