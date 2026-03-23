package baekjoon.clazz.class3.s3_17626;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int[] arr = new int[50001];

    public static void main(String[] args) throws IOException {
        Arrays.fill(arr, 5);
        dfs(0, 0, 0);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        System.out.println(arr[n]);
    }

    static void dfs(int depth, int start, int cur) {
        if (cur > 50000) return;
        arr[cur] = Math.min(arr[cur], depth);
        if (depth >= 4) return;

        for (int i = start; i * i <= 50000; ++i) {
            dfs(depth + 1, i, cur + i * i);
        }
    }
}
