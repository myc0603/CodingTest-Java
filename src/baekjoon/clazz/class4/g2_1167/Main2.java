package baekjoon.clazz.class4.g2_1167;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main2 {
    static int v;
    static ArrayList<int[]>[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        v = Integer.parseInt(br.readLine());

        tree = new ArrayList[v + 1];
        for (int i = 1; i <= v; ++i) tree[i] = new ArrayList<>();

        StringTokenizer st;
        for (int i = 1; i <= v; ++i) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            while (to != -1) {
                int w = Integer.parseInt(st.nextToken());
                tree[from].add(new int[]{to, w});

                to = Integer.parseInt(st.nextToken());
            }
        }

        int node = bfs(1)[0];
        int ans = bfs(node)[1];
        System.out.println(ans);
    }

    // [node, dist]
    static int[] bfs(int start) {
        int[] dist = new int[v + 1];
        Arrays.fill(dist, -1);

        Queue<Integer> q = new ArrayDeque<>();
        q.add(start);
        dist[start] = 0;

        int[] maxNode = {0, 0};

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int[] next : tree[cur]) {
                int to = next[0];
                int w = next[1];

                if (dist[to] != -1) continue;
                dist[to] = dist[cur] + w;
                if (dist[to] > maxNode[1]) {
                    maxNode[0] = to;
                    maxNode[1] = dist[to];
                }

                q.add(to);
            }
        }

        return maxNode;
    }
}
