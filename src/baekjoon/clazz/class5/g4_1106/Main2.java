package baekjoon.clazz.class5.g4_1106;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main2 {
    static int c, n, INF = 100000 * 2;
    static int[][] cities;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        c = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        // [cost, cnt]
        cities = new int[n][2];
        dp = new int[c + 1];
        Arrays.fill(dp, INF);

        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            cities[i][0] = Integer.parseInt(st.nextToken());
            cities[i][1] = Integer.parseInt(st.nextToken());
        }

        dp[0] = 0;
        for (int[] city : cities) {
            int cost = city[0];
            int cnt = city[1];
            for (int j = 0; j <= c; ++j) {
                dp[j] = Math.min(dp[Math.max(0, j - cnt)] + cost, dp[j]);
            }
        }

        System.out.println(dp[c]);
    }
}
