package baekjoon.tier.gold.g5_2294;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main2 {
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

        for (int i = 0; i < n; ++i) coins[i] = Integer.parseInt(br.readLine());

        dp[0][0] = 0;
        for (int i = 0; i < n; ++i) {
            int coin = coins[i];
            for (int j = 0; j <= k; ++j) {
                if (i > 0 && dp[i - 1][j] != -1) dp[i][j] = dp[i - 1][j];
                if (j - coin >= 0 && dp[i][j - coin] != -1) {
                    if (dp[i][j] != -1) dp[i][j] = Math.min(dp[i][j], dp[i][j - coin] + 1);
                    else dp[i][j] = dp[i][j - coin] + 1;
                }
            }
        }

        System.out.println(dp[n - 1][k]);
    }
}
