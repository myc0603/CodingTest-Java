package baekjoon.clazz.class5.g3_11049;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// bottom up
public class Standard {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // ith matrix: dim[i] * dim[i + 1]
        long[] dim = new long[n + 1];

        StringTokenizer st;
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            dim[i] = Integer.parseInt(st.nextToken());
            if (i == n - 1) dim[i + 1] = Integer.parseInt(st.nextToken());
        }

        long[][] dp = new long[n][n];
        for (int len = 2; len <= n; ++len) {
            for (int i = 0; i <= n - len; ++i) {
                int j = i + len - 1;
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; ++k) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j] + dim[i] * dim[k + 1] * dim[j + 1]);
                }
            }
        }

        System.out.println(dp[0][n - 1]);
    }
}
