package baekjoon.clazz.class5.g1_1562;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class TopDownDP {
    static int MOD = 1000000000;
    static long[][][] dp = new long[101][10][1024];

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 101; ++i) {
            for (int j = 0; j < 10; ++j) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        long ans = 0;
        for (int last = 0; last <= 9; ++last) ans = (ans + dfs(n, last, (1 << 10) - 1)) % MOD;

        System.out.println(ans);
    }

    static long dfs(int len, int last, int mask) {
        if (len == 0) return 0;
        if (len == 1) {
            if (last == 0) return 0;
            if (mask == (1 << last)) return 1;
            return 0;
        }
        // mask에 last가 마스킹 되어있어야 함
        if ((mask & (1 << last)) == 0) return 0;

        if (dp[len][last][mask] != -1) return dp[len][last][mask];

        // 이전에 last를 썼을 수도 있음
        dp[len][last][mask] = 0;
        int prevMask = mask & ~(1 << last);
        if (last < 9) {
            dp[len][last][mask] = (dp[len][last][mask] + dfs(len - 1, last + 1, prevMask)) % MOD;
            dp[len][last][mask] = (dp[len][last][mask] + dfs(len - 1, last + 1, mask)) % MOD;
        }
        if (last > 0) {
            dp[len][last][mask] = (dp[len][last][mask] + dfs(len - 1, last - 1, prevMask)) % MOD;
            dp[len][last][mask] = (dp[len][last][mask] + dfs(len - 1, last - 1, mask)) % MOD;
        }

        return dp[len][last][mask];
    }
}
