package baekjoon.week.week2.practice;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class DfsStack {
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

        dfsIterative(1);
    }

    static void initGraph(int v) {
        adj = new ArrayList[v + 1];
        for (int i = 1; i <= v; ++i) adj[i] = new ArrayList<>();
    }

    static void addEdge(int a, int b) {
        adj[a].add(b);
        adj[b].add(a);
    }

    static void dfsIterative(int start) {
        Deque<Integer> stk = new ArrayDeque<>();
        stk.push(start);
        visited[start] = true;

        while (!stk.isEmpty()) {
            int cur = stk.pop();
            System.out.println(cur + " 방문");

            for (int nxt : adj[cur]) {
                if (visited[nxt]) continue;
                visited[nxt] = true;
                stk.push(nxt);
            }
        }
    }
}
