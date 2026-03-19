package baekjoon.tier.gold.g4_2293;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// top down dp 더 나은 점화식
public class Standard1 {
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

    // 한 번의 dfs 호출에서 coins[idx]를 쓰냐 안쓰냐 두가지만 고려
    // 한 번 쓰고나서 또 쓸지 말지는 다음 호출에서 결정
    static int dfs(int idx, int cur) {
        if (cur == 0) return 1;
        if (idx < 0 || cur < 0) return 0;
        if (dp[idx][cur] != -1) return dp[idx][cur];

        return dp[idx][cur] = dfs(idx - 1, cur) + dfs(idx, cur - coins[idx]);
    }

}
