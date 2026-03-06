package baekjoon.week.week3.e12869_g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DP {

    static int n, a, b, c;
    static int[][] attacks = {
            {9, 3, 1}, {3, 9, 1},
            {9, 1, 3}, {1, 9, 3},
            {3, 1, 9}, {1, 3, 9}
    };
    static int[][][] dp = new int[61][61][61];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        if (n >= 2) b = Integer.parseInt(st.nextToken());
        if (n >= 3) c = Integer.parseInt(st.nextToken());

        System.out.println(solve(a, b, c));
    }

    static int solve(int a, int b, int c) {
        a = Math.max(0, a);
        b = Math.max(0, b);
        c = Math.max(0, c);

        if (a == 0 && b == 0 && c == 0) return 0;
        if (dp[a][b][c] != 0) return dp[a][b][c];

        int result = Integer.MAX_VALUE;
        for (int[] atk : attacks) {
            result = Math.min(result, solve(a - atk[0], b - atk[1], c - atk[2]) + 1);
        }
        return dp[a][b][c] = result;
    }
}
