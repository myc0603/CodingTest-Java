package baekjoon.tier.gold.g4_2293;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// top down dp
public class Main {
    static int n, k;
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

        for (int i = 0; i < n; ++i) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        System.out.println(dfs(n - 1, k));
    }

    static int dfs(int idx, int cur) {
        if (idx == 0) {
            if (cur % coins[0] == 0) return 1;
            else return 0;
        }

        if (dp[idx][cur] != -1) return dp[idx][cur];

        int result = 0;
        for (int i = 0; cur - coins[idx] * i >= 0; ++i) {
            result += dfs(idx - 1, cur - coins[idx] * i);
        }
        return dp[idx][cur] = result;
    }
}
