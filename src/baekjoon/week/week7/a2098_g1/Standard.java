package baekjoon.week.week7.a2098_g1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Standard {
    static int n;
    static int[][] w;
    static int[][] dp;

    static final int INF = 100_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        w = new int[n][n];
        dp = new int[n][1 << n];
        for (int i = 0; i < n; ++i) Arrays.fill(dp[i], -1);

        StringTokenizer st;
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; ++j) {
                w[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(dfs(0, 1));
    }

    static int dfs(int cur, int mask) {
        if (mask == ((1 << n) - 1)) {
            return w[cur][0] == 0 ? INF : w[cur][0];
        }

        if (dp[cur][mask] != -1) return dp[cur][mask];

        int ret = INF;
        for (int next = 0; next < n; ++next) {
            if ((mask & (1 << next)) != 0) continue;
            if (w[cur][next] == 0) continue;
            ret = Math.min(ret, dfs(next, mask | (1 << next)) + w[cur][next]);
        }

        return dp[cur][mask] = ret;
    }
}
