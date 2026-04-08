package baekjoon.week.week7.a2098_g1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class GPTFeedback {
    static int n;
    static int[][] w;
    static int[][] dp;

    static final int INF = 100_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        w = new int[n][n];
        dp = new int[n][1 << n];
        for (int i = 0; i < n; ++i) Arrays.fill(dp[i], INF);

        StringTokenizer st;
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; ++j) {
                w[i][j] = Integer.parseInt(st.nextToken());
                if (i != j && w[i][j] == 0) w[i][j] = INF;
            }
        }

        dp[0][1] = 0;
        for (int mask = 0; mask < (1 << n); ++mask) {
            for (int last = 0; last < n; ++last) {
//            for (int mask = 0; mask < (1 << n); ++mask) {
                if (dp[last][mask] == INF) continue;
                if ((mask & (1 << last)) == 0) continue;

                for (int next = 0; next < n; ++next) {
                    if (next == last) continue;
                    if ((mask & (1 << next)) > 0) continue;

                    int nextMask = mask | (1 << next);
                    dp[next][nextMask] = Math.min(dp[next][nextMask], dp[last][mask] + w[last][next]);
                }
            }
        }

        int ans = INF;
        for (int last = 1; last < n; ++last) {
            ans = Math.min(ans, dp[last][(1 << n) - 1] + w[last][0]);
        }
        System.out.println(ans);
    }
}
