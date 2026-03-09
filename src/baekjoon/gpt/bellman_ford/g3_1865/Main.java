package baekjoon.gpt.bellman_ford.g3_1865;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static class Edge {
        final int f, t, w;
        Edge(int f, int t, int w) {
            this.f = f;
            this.t = t;
            this.w = w;
        }
    }

    static int T, n, m, w;
    static Edge[] edges;
    static boolean[] canComeback;
    static long[] dist;
    static final long INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        canComeback = new boolean[T];

        StringTokenizer st;
        for (int t = 0; t < T; ++t) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            dist = new long[n + 1];
//            Arrays.fill(dist, INF);
            edges = new Edge[2 * m + w];

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());

                edges[i * 2] = new Edge(from, to, weight);
                edges[i * 2 + 1] = new Edge(to, from, weight);
            }

            for (int i = 0; i < w; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());

                edges[2 * m + i] = new Edge(from, to, -weight);
            }

            // bellman-ford
            // 출발지에서 다시 출발지로 돌아왔을 때 시간이 되돌아가는 경우
            // => 음의 사이클이 있는 경우
            /*
              틀린 이유
              1에서 출발하는데 1과 연결되어 있지 않은 사이클은 검출이 안 됨
              1과 연결되어 있지 않은 노드는 계속 dist[..] 값이 INF 이기 때문
              => 모든 지점을 출발점으로 처리(~ 모든 지점이 가중치가 0인 간선으로 이어져있다고 생각할 수 있음)
             */
//            dist[1] = 0;
            for (int i = 0; i < n; ++i) {
                for (Edge e : edges) {
                    if (dist[e.f] != INF && dist[e.t] > dist[e.f] + e.w) {
                        if (i == n - 1) canComeback[t] = true;
                        dist[e.t] = dist[e.f] + e.w;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (boolean b : canComeback) sb.append(b ? "YES" : "NO").append('\n');
        System.out.println(sb);
    }
}
