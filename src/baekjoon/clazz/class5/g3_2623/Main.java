package baekjoon.clazz.class5.g3_2623;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 메모리초과
public class Main {
    static class Node {
        int value;
        ArrayList<Node> next = new ArrayList<>();
        ArrayList<Node> prev = new ArrayList<>();

        Node(int value) {
            this.value = value;
        }
    }

    static int n, m;
    static Node[] nodes;

    static boolean[] visited, path;

    public static void main(String[] args) throws IOException {
        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        nodes = new Node[n + 1];
        for (int i = 1; i <= n; ++i) nodes[i] = new Node(i);

        for (int i = 0; i < m; ++i) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());

            int prev = Integer.parseInt(st.nextToken());
            for (int j = 0; j < k - 1; ++j) {
                int cur = Integer.parseInt(st.nextToken());
                nodes[prev].next.add(nodes[cur]);
                nodes[cur].prev.add(nodes[prev]);

                prev = cur;
            }
        }

        // cycle check
        visited = new boolean[n + 1];
        path = new boolean[n + 1];

        for (int i = 1; i <= n; ++i) {
            if (visited[i]) continue;
            if (hasCycle(i)) {
                System.out.println(0);
                return;
            }
            path[i] = false;
        }

        // get answer
        StringBuilder sb = new StringBuilder();
        ArrayList<Integer> result = sol();
        boolean[] isUsed = new boolean[n + 1];
        for (int i = result.size() - 1; i >= 0; --i) {
            int x = result.get(i);
            if (isUsed[x]) continue;
            sb.append(x).append('\n');
            isUsed[x] = true;
        }

        System.out.println(sb);
    }

    static boolean hasCycle(int cur) {
        visited[cur] = true;
        path[cur] = true;

        for (Node next : nodes[cur].next) {
            if (path[next.value]) return true;
            if (visited[next.value]) continue;
            if (hasCycle(next.value)) return true;
            path[next.value] = false;
        }

        return false;
    }

    static ArrayList<Integer> sol() {
        ArrayList<Integer> result = new ArrayList<>();
        ArrayDeque<Integer> q = new ArrayDeque<>();

        for (int i = 1; i <= n; ++i) {
            if (nodes[i].next.isEmpty()) q.add(i);
        }

        while (!q.isEmpty()) {
            int cur = q.poll();
            result.add(cur);

            for (Node prev : nodes[cur].prev) {
                q.add(prev.value);
            }
        }

        return result;
    }
}
