package baekjoon.category.topology_sort.g3_2252;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
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
            int smaller = Integer.parseInt(st.nextToken());
            int taller = Integer.parseInt(st.nextToken());

            graph[smaller].add(taller);
            indegree[taller]++;
        }

        StringBuilder sb = new StringBuilder();

        ArrayDeque<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= n; ++i) {
            if (indegree[i] == 0) q.add(i);
        }

        while (!q.isEmpty()) {
            int cur = q.poll();
            sb.append(cur).append(' ');

            for (Integer next : graph[cur]) {
                indegree[next]--;
                if (indegree[next] == 0) q.add(next);
            }
        }

        System.out.println(sb);
    }
}
