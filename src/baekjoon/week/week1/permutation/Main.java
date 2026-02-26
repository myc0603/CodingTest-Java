package baekjoon.week.week1.permutation;

import java.util.Arrays;

public class Main {

    static int[] arr = {1, 2, 3, 4, 5, 6};
    static int n = arr.length;
    static int r = 3;

    static boolean[] visited = new boolean[n];
    static int[] result = new int[r];

    static int cnt = 0;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        perm(0);
        System.out.println(sb);
    }

    private static void perm(int depth) {
        if (depth == r) {
            // result 출력
            sb.append(++cnt).append(" - ").append(Arrays.toString(result)).append('\n');
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
