package baekjoon.clazz.class5.g1_1562;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Standard {
    static final int MOD = 1_000_000_000;
    static final int FULL = (1 << 10) - 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][][] dp = new int[n + 1][10][1 << 10];

        for (int d = 1; d <= 9; ++d) {
            dp[1][d][1 << d] = 1;
        }

        // len, d, mask 세 변수를 같은 레벨의 변수로 => cur
        for (int len = 1; len < n; ++len) {
            for (int d = 0; d <= 9; ++d) {
                for (int mask = 0; mask < (1 << 10); ++mask) {
                    int cur = dp[len][d][mask];
                    if (cur == 0) continue;

                    if (d > 0) {
                        int next = d - 1;
                        int nextMask = mask | (1 << next);
                        dp[len + 1][next][nextMask] = (dp[len + 1][next][nextMask] + cur) % MOD;
                    }

                    if (d < 9) {
                        int next = d + 1;
                        int nextMask = mask | (1 << next);
                        dp[len + 1][next][nextMask] = (dp[len + 1][next][nextMask] + cur) % MOD;
                    }
                }
            }
        }

        long ans = 0;
        for (int d = 0; d <= 9; ++d) {
            ans = (ans + dp[n][d][FULL]) % MOD;
        }
        System.out.println(ans);
    }
}
