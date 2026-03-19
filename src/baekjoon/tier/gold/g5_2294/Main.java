package baekjoon.tier.gold.g5_2294;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n, k, MAX = 100000;
    static int[] coins;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        coins = new int[n];
        dp = new int[n][k + 1];
        for (int i = 0; i < n; ++i) Arrays.fill(dp[i], -1);

        for (int i = 0; i < n; ++i) coins[i] = Integer.parseInt(br.readLine());

        int ans = dfs(n - 1, k);
        System.out.println(ans >= MAX ? -1 : ans);
    }

    static int dfs(int idx, int cur) {
        if (cur == 0) return 0;
        if (cur < 0 || idx < 0) return MAX;

        if (dp[idx][cur] != -1) return dp[idx][cur];

        return dp[idx][cur] = Math.min(dfs(idx, cur - coins[idx]) + 1, dfs(idx - 1, cur));
    }
}
