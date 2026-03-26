package practice.bfs_dfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;

public class BfsShortestPath {
    static int V = 6;
    static ArrayList<Integer>[] adj;

    public static void main(String[] args) {
        initGraph(V);

        addEdge(1, 2);
        addEdge(1, 3);
        addEdge(2, 4);
        addEdge(3, 5);
        addEdge(5, 6);

        int start = 1;
        int[] dist = bfsDist(start);

        for (int i = 1; i <= V; i++) {
            System.out.println("dist[" + i + "] = " + dist[i]);
        }

    }

    static void initGraph(int v) {
        adj = new ArrayList[v + 1];
        for (int i = 1; i <= v; i++) adj[i] = new ArrayList<>();
    }

    static void addEdge(int a, int b) {
        adj[a].add(b);
        adj[b].add(a);
    }

    static int[] bfsDist(int start) {
        int[] dist = new int[V + 1];
        Arrays.fill(dist, -1);

        Deque<Integer> q = new ArrayDeque<>();
        q.add(start);
        dist[start] = 0;

        while (!q.isEmpty()) {
            int cur = q.poll();
            System.out.println(cur);

            for (int next : adj[cur]) {
                if (dist[next] != -1) continue;

                dist[next] = dist[cur] + 1;
                q.add(next);
            }
        }
        return dist;
    }
}
