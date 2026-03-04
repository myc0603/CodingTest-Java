package baekjoon.week.week2.practice;

import java.util.ArrayList;

public class DfsRecursive {

    static int N = 6;
    static ArrayList<Integer>[] adj;
    static boolean[] visited;

    public static void main(String[] args) {
        initGraph(N);

        addEdge(1, 2);
        addEdge(1, 3);
        addEdge(2, 4);
        addEdge(3, 5);
        addEdge(5, 6);

        visited = new boolean[N + 1];

        dfs(1);
    }

    static void initGraph(int v) {
        adj = new ArrayList[v + 1];
        for (int i = 1; i <= v; ++i) adj[i] = new ArrayList<>();
    }

    static void addEdge(int a, int b) {
        adj[a].add(b);
        adj[b].add(a);
    }

    static void dfs(int cur) {
        visited[cur] = true;
        System.out.println(cur);

        for (int nxt : adj[cur]) {
            if (visited[nxt]) continue;
            dfs(nxt);
        }
    }
}
