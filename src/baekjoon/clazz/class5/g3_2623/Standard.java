package baekjoon.clazz.class5.g3_2623;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Standard {
    static int n, m;
    static ArrayList<Integer>[] graph;
    static int[] indegree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; ++i) graph[i] = new ArrayList<>();
        indegree = new int[n + 1];

        for (int i = 0; i < m; ++i) {
            st = new StringTokenizer(br.readLine());

            int k = Integer.parseInt(st.nextToken());

            int prev = Integer.parseInt(st.nextToken());
            for (int j = 0; j < k - 1; ++j) {
                int cur = Integer.parseInt(st.nextToken());
                graph[prev].add(cur);
                indegree[cur]++;

                prev = cur;
            }
        }

        ArrayDeque<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= n; ++i) {
            if (indegree[i] == 0) q.add(i);
        }

        int cnt = 0;
        StringBuilder sb = new StringBuilder();
        while (!q.isEmpty()) {
            int cur = q.poll();
            sb.append(cur).append('\n');
            ++cnt;

            for (int next : graph[cur]) {
                indegree[next]--;
                if (indegree[next] == 0) {
                    q.add(next);
                }
            }
        }

        if (cnt == n) System.out.println(sb);
        else System.out.println(0);
    }
}
