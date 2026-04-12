package baekjoon.category.topology_sort.g3_1516;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static ArrayList<Integer>[] graph;
    static int[] indegree;
    static int[] times;
    static int[] answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; ++i) graph[i] = new ArrayList<>();
        indegree = new int[n + 1];
        times = new int[n + 1];
        answer = new int[n + 1];

        StringTokenizer st;
        for (int i = 1; i <= n; ++i) {
            st = new StringTokenizer(br.readLine());

            times[i] = Integer.parseInt(st.nextToken());

            int prev = Integer.parseInt(st.nextToken());
            while (prev != -1) {
                graph[prev].add(i);
                indegree[i]++;

                prev = Integer.parseInt(st.nextToken());
            }
        }

        // topology sort
        ArrayDeque<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= n; ++i) {
            if (indegree[i] == 0) {
                q.add(i);
                answer[i] = times[i];
            }
        }

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (Integer next : graph[cur]) {
                indegree[next]--;
                answer[next] = Math.max(answer[next], answer[cur] + times[next]);
                if (indegree[next] == 0) {
                    q.add(next);
                }
            }
        }


        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; ++i) sb.append(answer[i]).append('\n');
        System.out.println(sb);
    }
}
