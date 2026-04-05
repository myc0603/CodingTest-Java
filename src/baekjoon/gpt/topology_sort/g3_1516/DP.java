package baekjoon.gpt.topology_sort.g3_1516;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class DP {
    static int n;
    static ArrayList<Integer>[] prevGraph;
    static int[] costs, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        prevGraph = new ArrayList[n + 1];
        for (int i = 1; i <= n; ++i) prevGraph[i] = new ArrayList<>();

        costs = new int[n + 1];
        dp = new int[n + 1];

        StringTokenizer st;
        for (int i = 1; i <= n; ++i) {
            st = new StringTokenizer(br.readLine());

            costs[i] = Integer.parseInt(st.nextToken());

            int prev = Integer.parseInt(st.nextToken());
            while (prev != -1) {
                prevGraph[i].add(prev);

                prev = Integer.parseInt(st.nextToken());
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; ++i) sb.append(dfs(i)).append('\n');
        System.out.println(sb);
    }

    static int dfs(int cur) {
        if (dp[cur] != 0) return dp[cur];

        int maxPrev = 0;
        for (Integer prev : prevGraph[cur]) {
            maxPrev = Math.max(maxPrev, dfs(prev));
        }

        return dp[cur] = maxPrev + costs[cur];
    }
}
