package baekjoon.clazz.class4.g2_1167;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int v, ans;
    static ArrayList<int[]>[] tree;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        v = Integer.parseInt(br.readLine());

        tree = new ArrayList[v + 1];
        for (int i = 1; i <= v; ++i) tree[i] = new ArrayList<>();
        visited = new boolean[v + 1];

        StringTokenizer st;
        for (int i = 0; i < v; ++i) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            while (to != -1) {
                int weight = Integer.parseInt(st.nextToken());
                tree[from].add(new int[]{to, weight});

                to = Integer.parseInt(st.nextToken());
            }
        }

        dfs(1);

        System.out.println(ans);
    }

    static int dfs(int cur) {
        visited[cur] = true;

        int best1 = 0;
        int best2 = 0;
        for (int[] next : tree[cur]) {
            int to = next[0];
            int w = next[1];
            if (visited[to]) continue;

            int len = dfs(to) + w;

            if (len > best1) {
                best2 = best1;
                best1 = len;
            } else if (len > best2) {
                best2 = len;
            }
        }

        ans = Math.max(ans, best1 + best2);
        return best1;
    }
}
