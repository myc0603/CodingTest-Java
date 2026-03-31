package baekjoon.clazz.class5.g3_11049;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] arr;

    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        arr = new int[n][2];
        dp = new int[n][n];
        for (int i = 0; i < n; ++i) Arrays.fill(dp[i], Integer.MAX_VALUE);

        StringTokenizer st;
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        System.out.println(dfs(0, n - 1));
    }

    static int dfs(int i, int j) {
        if (i == j) return 0;

        if (dp[i][j] != Integer.MAX_VALUE) return dp[i][j];

        for (int k = i; k < j; ++k) {
            dp[i][j] = Math.min(dp[i][j], dfs(i, k) + dfs(k + 1, j) + arr[i][0] * arr[k][1] * arr[j][1]);
        }
        return dp[i][j];
    }
}
