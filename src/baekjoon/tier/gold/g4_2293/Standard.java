package baekjoon.tier.gold.g4_2293;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 1d dp
public class Standard {
    static int n, k;
    static int[] coins;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        coins = new int[n];
        dp = new int[k + 1];

        for (int i = 0; i < n; ++i) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        dp[0] = 1;
        for (int coin : coins) {
            for (int sum = coin; sum <= k; ++sum) {
                dp[sum] += dp[sum - coin];
            }
        }
        System.out.println(dp[k]);
    }

}
