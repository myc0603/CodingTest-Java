package baekjoon.week.week2.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Bfs2D {
    static int n, m;
    static int[][] grid;
    static int[][] dist;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        grid = new int[n][m];
        dist = new int[n][m];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], -1);
        }

        for (int i = 0; i < n; i++) {
            String line = br.readLine().trim();
            for (int j = 0; j < m; ++j) {
                grid[i][j] = line.charAt(j) - '0';
            }
        }

        int ans = bfs(0, 0, n - 1, m - 1);
        System.out.println(ans);
    }

    static int bfs(int sy, int sx, int ty, int tx) {
        if (!inMap(sy, sx) || grid[sy][sx] == 0) return -1;

        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{sy, sx});
        dist[sy][sx] = 0;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int y = cur[0], x = cur[1];

            if (y == ty && x == tx) return dist[y][x];

            for (int i = 0; i < 4; ++i) {
                int ny = y + dy[i];
                int nx = x + dx[i];

                if (!inMap(ny, nx)) continue;
                if (grid[ny][nx] == 0) continue;
                if (dist[ny][nx] != -1) continue;

                dist[ny][nx] = dist[y][x] + 1;
                q.add(new int[]{ny, nx});
            }
        }

        return -1;
    }

    static boolean inMap(int y, int x) {
        return 0 <= y && y < n && 0 <= x && x < m;
    }
}

/*
4 5
11111
10001
10101
11111
*/
