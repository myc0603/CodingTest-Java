package baekjoon.gpt.dijkstra.g4_1753;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class State {
        int node, dist;

        State(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }
    }

    static final int INF = 10_000_000;
    static int v, e, start;
    static ArrayList<int[]>[] graph;
    static int[] dist;

    public static void main(String[] args) throws IOException {
        // 입력 및 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        graph = new ArrayList[v + 1];
        for (int i = 1; i <= v; i++) graph[i] = new ArrayList<>();

        dist = new int[v + 1];
        Arrays.fill(dist, INF);

        start = Integer.parseInt(br.readLine());

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph[u].add(new int[]{v, w});
        }

        // 풀이
        PriorityQueue<State> pq = new PriorityQueue<>(Comparator.comparingInt(s -> s.dist));
        pq.offer(new State(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            State cur = pq.poll();

            if (cur.dist > dist[cur.node]) continue;

            for (int[] next : graph[cur.node]) {
                int nextNode = next[0];
                int nd = cur.dist + next[1];

                if (dist[nextNode] > nd) {
                    dist[nextNode] = nd;
                    pq.offer(new State(nextNode, nd));
                }
            }
        }

        // 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= v; i++) {
            if (dist[i] == INF) sb.append("INF").append('\n');
            else sb.append(dist[i]).append('\n');
        }
        System.out.println(sb);
    }
}
