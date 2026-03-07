package baekjoon.clazz.class2.b1_2775;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int t, n, k;
    static int[][] dp = new int[16][16];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; ++i) {
            k = Integer.parseInt(br.readLine());
            n = Integer.parseInt(br.readLine());
            System.out.println(solve(k, n));
        }


    }

    static int solve(int a, int b) {
        if (a == 0) {
            return b;
        }

        if (dp[a][b] != 0) return dp[a][b];

        int res = 0;
        for (int i = 1; i <= b; ++i) {
            res += solve(a - 1, i);
        }
        return dp[a][b] = res;
    }

}
