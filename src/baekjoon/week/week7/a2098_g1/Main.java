package baekjoon.week.week7.a2098_g1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] w;
    static int[][][] dp;

    static final int INF = 100_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        w = new int[n][n];
        dp = new int[n + 1][n][1 << n];
        for (int i = 0; i <= n; ++i) {
            for (int j = 0; j < n; ++j) {
                Arrays.fill(dp[i][j], INF);
            }
        }

        StringTokenizer st;
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; ++j) {
                w[i][j] = Integer.parseInt(st.nextToken());
                if (i != j && w[i][j] == 0) w[i][j] = INF;
            }
        }

        dp[1][0][1] = 0;
//        for (int start = 0; start < n; ++start) {
//            dp[1][start][1 << start] = 0;
//        }

        for (int cnt = 1; cnt < n; ++cnt) {
            for (int last = 0; last < n; ++last) {
                for (int mask = 0; mask < (1 << n); ++mask) {
                    if ((mask & (1 << last)) == 0) continue;

                    for (int next = 0; next < n; ++next) {
                        // next == last 인 경우가 포함됨
                        if ((mask & (1 << next)) > 0) continue;

                        int nextMask = mask | (1 << next);
                        dp[cnt + 1][next][nextMask] = Math.min(dp[cnt + 1][next][nextMask], dp[cnt][last][mask] + w[last][next]);
                    }
                }
            }
        }

        int ans = INF;
        for (int last = 1; last < n; ++last) {
            ans = Math.min(ans, dp[n][last][(1 << n) - 1] + w[last][0]);
        }
        System.out.println(ans);
    }
}
