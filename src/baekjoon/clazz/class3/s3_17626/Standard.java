package baekjoon.clazz.class3.s3_17626;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Standard {
    static int[] dp = new int[50001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Arrays.fill(dp, 5);

        dp[0] = 0;
        for (int i = 1; i <= 50000; ++i) {
            for (int j = 1; i - j * j >= 0; ++j) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }

        System.out.println(dp[n]);
    }
}
