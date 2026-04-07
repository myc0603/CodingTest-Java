package baekjoon.clazz.class5.g1_1562;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BottomUpDP {
    static final int MOD = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][][] dp = new int[101][10][1 << 10];

        for (int d = 1; d <= 9; ++d) {
            dp[1][d][1 << d] = 1;
        }

        for (int len = 2; len <= n; ++len) {
            for (int d = 0; d <= 9; ++d) {
                for (int mask = 0; mask < (1 << 10); ++mask) {

                    if (d < 9 && (mask & (1 << (d + 1))) > 0) {
                        if ((mask & (1 << (d + 1))) == 0) continue;
                        dp[len][d][mask | 1 << d] = (dp[len][d][mask | 1 << d] + dp[len - 1][d + 1][mask]) % MOD;
                    }

                    if (d > 0 && (mask & (1 << (d - 1))) > 0) {
                        if ((mask & (1 << (d - 1))) == 0) continue;
                        dp[len][d][mask | 1 << d] = (dp[len][d][mask | 1 << d] + dp[len - 1][d - 1][mask]) % MOD;
                    }
                }
            }
        }

        long ans = 0;
        for (int d = 0; d <= 9; ++d) {
            ans = (ans + dp[n][d][(1 << 10) - 1]) % MOD;
        }
        System.out.println(ans);
    }

}
