package baekjoon.clazz.class5.g3_2623;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class DfsTopologySort {
    static int n, m;
    static ArrayList<Integer>[] graph;

    static boolean[] visited;
    static boolean[] onPath;

    static ArrayList<Integer> result = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; ++i) graph[i] = new ArrayList<>();
        visited = new boolean[n + 1];
        onPath = new boolean[n + 1];

        for (int i = 0; i < m; ++i) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());

            int prev = Integer.parseInt(st.nextToken());
            for (int j = 0; j < k - 1; ++j) {
                int cur = Integer.parseInt(st.nextToken());
                graph[prev].add(cur);

                prev = cur;
            }
        }

        for (int i = 1; i <= n; ++i) {
            if (visited[i]) continue;
            if (hasCycle(i)) {
                System.out.println(0);
                return;
            }
        }

        StringBuilder sb = new StringBuilder();
        Collections.reverse(result);
        for (Integer x : result) {
            sb.append(x).append('\n');
        }
        System.out.println(sb);
    }

    static boolean hasCycle(int cur) {
        visited[cur] = true;
        onPath[cur] = true;

        for (int next : graph[cur]) {
            if (onPath[next]) return true;
            if (visited[next]) continue;

            if (hasCycle(next)) return true;
        }

        onPath[cur] = false;
        result.add(cur);

        return false;
    }
}
