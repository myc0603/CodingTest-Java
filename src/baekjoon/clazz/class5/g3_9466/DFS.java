package baekjoon.clazz.class5.g3_9466;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DFS {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int n;
    static int[] choices;
    static boolean[] visited;
    static boolean[] finished;
    static int teamCount;

    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            solve();
        }
        System.out.println(sb);
    }

    static void solve() throws IOException {
        n = Integer.parseInt(br.readLine());

        choices = new int[n + 1];
        visited = new boolean[n + 1];
        finished = new boolean[n + 1];
        teamCount = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; ++i) {
            choices[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= n; ++i) {
            if (visited[i]) continue;
            dfs(i);
        }

        sb.append(n - teamCount).append('\n');
    }

    static void dfs(int cur) {
        visited[cur] = true;
        int next = choices[cur];

        if (!visited[next]) dfs(next);
        else if (!finished[next]) { // cycle
            for (int x = next; x != cur; x = choices[x]) {
                teamCount++;
            }
            teamCount++;
        }

        finished[cur] = true;
    }
}
