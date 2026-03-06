package baekjoon.week.week3.b2589_g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

    static int n, m, ans;
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
            for (int j = 0; j < m; j++) {
                if (line.charAt(j) == 'L') a[i][j] = 1;
                else a[i][j] = 0;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (a[i][j] == 0) continue;
                bfs(i, j);
                dist = new int[n][m];
            }
        }

        System.out.println(ans - 1);
    }

    static void bfs(int sy, int sx) {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        dist[sy][sx] = 1;
        q.add(new int[]{sy, sx});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int y = cur[0], x = cur[1];

            ans = Math.max(ans, dist[y][x]);

            for (int i = 0; i < 4; ++i) {
                int ny = y + dy[i];
                int nx = x + dx[i];
                if (ny < 0 || n <= ny || nx < 0 || m <= nx) continue;
                if (a[ny][nx] == 0) continue;
                if (dist[ny][nx] > 0) continue;

                dist[ny][nx] = dist[y][x] + 1;
                q.add(new int[]{ny, nx});
            }
        }
    }
}
