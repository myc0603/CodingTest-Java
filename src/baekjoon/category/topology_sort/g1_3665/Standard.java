package baekjoon.category.topology_sort.g1_3665;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

// https://chatgpt.com/g/g-p-680f776a01c88191ada3bb31d4505348-algorijeum-munjepuli/c/69d023b9-9344-83a4-af6d-10853a3ebc7f
public class Standard {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            solve();
        }

        System.out.println(sb);
    }

    static void solve() throws IOException {
        int n = Integer.parseInt(br.readLine());

        boolean[][] graph = new boolean[n + 1][n + 1];
        int[] indegree = new int[n + 1];

        HashSet<Integer> set = new HashSet<>();
        for (int i = 1; i <= n; ++i) set.add(i);

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; ++i) {
            int team = Integer.parseInt(st.nextToken());
            set.remove(team);
            for (Integer x : set) {
                graph[team][x] = true;
                indegree[x]++;
            }
        }

        int m = Integer.parseInt(br.readLine());
        for (int i = 1; i <= m; ++i) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (graph[a][b]) {
                graph[a][b] = false;
                indegree[b]--;

                graph[b][a] = true;
                indegree[a]++;
            } else {
                graph[a][b] = true;
                indegree[b]++;

                graph[b][a] = false;
                indegree[a]--;
            }
        }

        // topology sort
        ArrayList<Integer> result = new ArrayList<>();
        ArrayDeque<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= n; ++i) {
            if (indegree[i] == 0) q.add(i);
        }

        int cnt = 0;
        while (!q.isEmpty()) {
            int cur = q.poll();
            result.add(cur);
            ++cnt;

            for (int i = 1; i <= n; ++i) {
                if (graph[cur][i]) {
                    indegree[i]--;
                    if (indegree[i] == 0) {
                        q.add(i);
                    }
                }
            }
        }

        if (cnt != n) {
            sb.append("IMPOSSIBLE\n");
        } else {
            for (Integer x : result) {
                sb.append(x).append(' ');
            }
            sb.append('\n');
        }
    }
}
