package practice.bfs_dfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class BfsQueue {
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
        bfs(1);

        System.out.println("================");

        visited = new boolean[N + 1];
        bfs2(1);
    }

    static void initGraph(int v) {
        adj = new ArrayList[v + 1];
        for (int i = 1; i <= v; ++i) adj[i] = new ArrayList<>();
    }

    static void addEdge(int a, int b) {
        adj[a].add(b);
        adj[b].add(a);
    }

    // queue에 넣을 때 visited 처리
    static void bfs(int start) {
        Deque<Integer> q = new ArrayDeque<>();
        q.add(start);
        visited[start] = true;

        while (!q.isEmpty()) {
            int cur = q.poll();
            System.out.println(cur);

            for (int nxt : adj[cur]) {
                if (visited[cur]) continue;
                visited[nxt] = true;
                q.add(nxt);
            }
        }
    }

    // 꺼낼 때 visited 처리
    static void bfs2(int start) {
        Deque<Integer> q = new ArrayDeque<>();
        q.add(start);

        while (!q.isEmpty()) {
            int cur = q.poll();
            if (visited[cur]) continue;

            visited[cur] = true;
            System.out.println(cur);

            for (int next : adj[cur]) {
                if (!visited[next]) q.add(next);
            }
        }
    }
}
