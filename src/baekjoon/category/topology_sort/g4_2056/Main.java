package baekjoon.category.topology_sort.g4_2056;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static ArrayList<Integer>[] graph;
    static int[] indegree, costs, times;
    static int tot;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; ++i) graph[i] = new ArrayList<>();
        indegree = new int[n + 1];
        costs = new int[n + 1];
        times = new int[n + 1];

        StringTokenizer st;
        for (int i = 1; i <= n; ++i) {
            st = new StringTokenizer(br.readLine());

            costs[i] = Integer.parseInt(st.nextToken());

            int m = Integer.parseInt(st.nextToken());
            while (m-- > 0) {
                int x = Integer.parseInt(st.nextToken());
                graph[x].add(i);
                indegree[i]++;
            }
        }

        ArrayDeque<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= n; ++i) {
            if (indegree[i] == 0) {
                q.add(i);
                times[i] = costs[i];
            }
        }

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (Integer next : graph[cur]) {
                indegree[next]--;
                times[next] = Math.max(times[next], times[cur] + costs[next]);
                if (indegree[next] == 0) q.add(next);
            }
        }

        for (int i = 1; i <= n; ++i) tot = Math.max(tot, times[i]);
        System.out.println(tot);
    }
}
