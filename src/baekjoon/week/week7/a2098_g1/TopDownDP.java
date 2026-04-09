package baekjoon.week.week7.a2098_g1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TopDownDP {
    static int n;
    static int[][] w;
    static int[][] dp;

    static final int INF = 100_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        w = new int[n][n];
        dp = new int[n][1 << n];
        // INF로 초기화하면 시간초과
        for (int i = 0; i < n; ++i) Arrays.fill(dp[i], -1);

        StringTokenizer st;
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; ++j) {
                w[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0][1] = 0;
        int ans = INF;
        for (int last = 1; last < n; ++last) {
            if (w[last][0] == 0) continue;
            ans = Math.min(ans, dfs(last, (1 << n) - 1) + w[last][0]);
        }
        System.out.println(ans);
    }

    // 0에서 출발해서 last로 끝나는 mask를 방문했을 때까지의 최소비용
    static int dfs(int last, int mask) {
        if ((mask & (1 << last)) == 0) return INF;
        if (last == 0) return mask == 1 ? 0 : INF; // dp[0][1] == 0 이라서 없어도 되긴 함
        if (dp[last][mask] != -1) return dp[last][mask];

        int ret = INF;
        int prevMask = mask & ~(1 << last);
        for (int prev = 0; prev < n; ++prev) {
            if ((prevMask & (1 << prev)) == 0) continue;
            if (w[prev][last] == 0) continue;

            ret = Math.min(ret, dfs(prev, prevMask) + w[prev][last]);
        }

        return dp[last][mask] = ret;
    }
}
