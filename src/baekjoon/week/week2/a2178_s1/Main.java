package baekjoon.week.week2.a2178_s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[][] a, dist;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        a = new int[n][m];
        dist = new int[n][m];

        for (int i = 0; i < n; ++i) {
            String line = br.readLine();
            Arrays.fill(dist[i], -1);
            for (int j = 0; j < m; ++j) a[i][j] = line.charAt(j) - '0';
        }

        bfs();

        System.out.println(dist[n - 1][m - 1]);
    }

    static void bfs() {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{0, 0});
        dist[0][0] = 1;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int y = cur[0], x = cur[1];

            for (int i = 0; i < 4; ++i) {
                int ny = y + dy[i];
                int nx = x + dx[i];

                if (!inMap(ny, nx)) continue;
                if (a[ny][nx] == 0) continue;
                if (dist[ny][nx] != -1) continue;

                dist[ny][nx] = dist[y][x] + 1;
                q.add(new int[]{ny, nx});
            }
        }
    }

    static boolean inMap(int y, int x) {
        return 0 <= y && y < n && 0 <= x && x < m;
    }
}
