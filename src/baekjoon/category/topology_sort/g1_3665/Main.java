package baekjoon.category.topology_sort.g1_3665;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            solve();
        }

        System.out.println(sb);
    }

    static void solve() throws IOException {
        int n = Integer.parseInt(br.readLine());

        HashSet<Integer>[] graph = new HashSet[n + 1];
        for (int i = 1; i <= n; ++i) graph[i] = new HashSet<>();
        int[] indegree = new int[n + 1];

        HashSet<Integer> tmp = new HashSet<>();
        for (int i = 1; i <= n; ++i) tmp.add(i);

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; ++i) {
            int x = Integer.parseInt(st.nextToken());
            tmp.remove(x);

            for (Integer y : tmp) {
                graph[x].add(y);
                indegree[y]++;
            }
        }

        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; ++i) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (graph[a].contains(b)) {
                graph[a].remove(b);
                indegree[b]--;

                graph[b].add(a);
                indegree[a]++;
            } else {
                graph[a].add(b);
                indegree[b]++;

                graph[b].remove(a);
                indegree[a]--;
            }
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 1; i <= n; ++i) {
            if (indegree[i] == 0) pq.add(i);
        }

        ArrayList<Integer> result = new ArrayList<>();
        while (!pq.isEmpty()) {
            if (pq.size() > 1) {
                sb.append("?\n");
                return;
            }
            int cur = pq.poll();
            result.add(cur);

            for (Integer next : graph[cur]) {
                indegree[next]--;
                if (indegree[next] == 0) pq.add(next);
            }
        }

        if (result.size() != n) {
            sb.append("IMPOSSIBLE\n");
            return;
        }

        for (Integer x : result) {
            sb.append(x).append(' ');
        }
        sb.append('\n');
    }
}
