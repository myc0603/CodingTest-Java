package baekjoon.week.week3.l1987_g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main3 {
    static int n, m;
    static char[][] a;
    static boolean[] visited = new boolean[26];
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        a = new char[n][m];

        for (int i = 0; i < n; ++i) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                a[i][j] = line.charAt(j);
            }
        }

        dfs(0, 0, 1);

        System.out.println(answer);

    }

    static void dfs(int y, int x, int cnt) {
        visited[a[y][x] - 'A'] = true;
        answer = Math.max(answer, cnt);

        if (answer == 26) return;

        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if (!inMap(ny, nx) || visited[a[ny][nx] - 'A']) continue;

            dfs(ny, nx, cnt + 1);
        }

        visited[a[y][x] - 'A'] = false;
    }

    static boolean inMap(int y, int x) {
        return 0 <= y && y < n && 0 <= x && x < m;
    }
}
