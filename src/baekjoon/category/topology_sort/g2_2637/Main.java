package baekjoon.category.topology_sort.g2_2637;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static ArrayList<int[]>[] prev;
    static TreeMap<Integer, Integer>[] dp;
    static boolean[] isBase;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        prev = new ArrayList[n + 1];
        for (int i = 1; i <= n; ++i) prev[i] = new ArrayList<>();
        dp = new TreeMap[n + 1];
        isBase = new boolean[n + 1];
        Arrays.fill(isBase, true);

        StringTokenizer st;
        for (int i = 0; i < m; ++i) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int k= Integer.parseInt(st.nextToken());

            prev[x].add(new int[]{y, k});
            isBase[x] = false;
        }

        TreeMap<Integer, Integer> result = dfs(n);

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, Integer> e : result.entrySet()) {
            sb.append(e.getKey()).append(' ').append(e.getValue()).append('\n');
        }
        System.out.println(sb);
    }

    static TreeMap<Integer, Integer> dfs(int cur) {
        if (dp[cur] != null) return dp[cur];

        TreeMap<Integer, Integer> result = new TreeMap<>();
        for (int[] p : prev[cur]) {
            int idx = p[0], cnt = p[1];

            if (isBase[idx]) {
                result.put(idx, result.getOrDefault(idx, 0) + cnt);
                continue;
            }

            TreeMap<Integer, Integer> tmp = dfs(idx);
            for (Map.Entry<Integer, Integer> e : tmp.entrySet()) {
                result.put(e.getKey(), result.getOrDefault(e.getKey(), 0) + cnt * e.getValue());
            }
        }

        return dp[cur] = result;
    }
}
