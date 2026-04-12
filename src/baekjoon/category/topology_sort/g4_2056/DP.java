package baekjoon.category.topology_sort.g4_2056;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DP {
    static int n;
    static int[] costs, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        costs = new int[n + 1];
        dp = new int[n + 1];

        StringTokenizer st;
        for (int i = 1; i <= n; ++i) {
            st = new StringTokenizer(br.readLine());

            costs[i] = Integer.parseInt(st.nextToken());
            dp[i] = costs[i];

            int m = Integer.parseInt(st.nextToken());
            while (m-- > 0) {
                int x = Integer.parseInt(st.nextToken());
                dp[i] = Math.max(dp[i], dp[x] + costs[i]);
            }
        }

        int answer = 0;
        for (int i = 1; i <= n; ++i) answer = Math.max(answer, dp[i]);
        System.out.println(answer);
    }
}
