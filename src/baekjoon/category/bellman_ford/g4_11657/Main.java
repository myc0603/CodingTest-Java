package baekjoon.category.bellman_ford.g4_11657;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static class Edge {
        final int from, to, weight;

        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    static int v, e;
    static Edge[] edges;
    static int[] dist;
    static boolean hasNegativeCycle;
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        edges = new Edge[e];
        dist = new int[v + 1];
        Arrays.fill(dist, INF);

        for (int i = 0; i < e; ++i) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            edges[i] = new Edge(from, to, weight);
        }

        dist[1] = 0;
        for (int i = 0; i < v; ++i) {
            for (Edge edge : edges) {
                // INF + weight -> overflow 발생해서 작은 값이 됨
                // dist[from]가 INF 라는 것의 의미 자체가 from 노드는 도달 불가능한 노드이기 때문에
                // release 하면 안됨
                if (dist[edge.from] != INF && dist[edge.to] > dist[edge.from] + edge.weight) {
                    // v번째 release, 음수 사이클 확인
                    if (i == v - 1) hasNegativeCycle = true;

                    dist[edge.to] = dist[edge.from] + edge.weight;
                }
            }
        }

        if (hasNegativeCycle) {
            System.out.println(-1);
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 2; i <= v; ++i) {
                sb.append(dist[i] == INF ? -1 : dist[i]).append('\n');
            }
            System.out.println(sb);
        }
    }
}
