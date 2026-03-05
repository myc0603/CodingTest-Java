package baekjoon.week.week2.s1325_s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Bfs {
    static int n, m;
    static ArrayList<Integer>[] tree;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        tree = new ArrayList[n + 1];
        for (int i = 0; i <= n; ++i) tree[i] = new ArrayList<>();
        visited = new boolean[n + 1];

        for (int i = 0; i < m; ++i) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            tree[b].add(a);
        }

        ArrayList<Integer> arr = new ArrayList<>();
        int maxCnt = 0;
        for (int i = 1; i <= n; ++i) {
//            int cnt = dfs(i);
            int cnt = bfs(i);
            if (cnt > maxCnt) {
                maxCnt = cnt;
                arr = new ArrayList<>();
                arr.add(i);
            } else if (cnt == maxCnt) {
                arr.add(i);
            }
            Arrays.fill(visited, false);
        }
        arr.sort(null);

        StringBuilder sb = new StringBuilder();
        for (int x : arr) sb.append(x).append(' ');
        System.out.println(sb);
    }

    static int dfs(int here) {
        visited[here] = true;

        int cnt = 1;
        for (int there : tree[here]) {
            if (visited[there]) continue;
            cnt += dfs(there);
        }
        return cnt;
    }

    static int bfs(int start) {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(start);
        visited[start] = true;

        int cnt = 0;
        while (!q.isEmpty()) {
            int cur = q.poll();
            ++cnt;

            for (int there : tree[cur]) {
                if (visited[there]) continue;
                visited[there] = true;
                q.add(there);
            }
        }
        return cnt;
    }
}
