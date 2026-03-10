package baekjoon.week.week3.j14497_g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Bfs01WithQueue {
    static int n, m, x1, y1, x2, y2;
    static char[][] a;
    static int[][] dist;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        a = new char[n][m];
        dist = new int[n][m];

        st = new StringTokenizer(br.readLine());
        y1 = Integer.parseInt(st.nextToken());
        x1 = Integer.parseInt(st.nextToken());
        y2 = Integer.parseInt(st.nextToken());
        x2 = Integer.parseInt(st.nextToken());
        --y1; --x1; --y2; --x2;

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                a[i][j] = line.charAt(j);
            }
        }

        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{y1, x1});
        dist[y1][x1] = 1;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int y = cur[0], x = cur[1];

            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];

                if (!inMap(ny, nx)) continue;

                int w = (a[ny][nx] == '1' || a[ny][nx] == '#') ? 1 : 0;
                if (dist[ny][nx] == 0 || dist[ny][nx] > dist[y][x] + w) {
                    dist[ny][nx] = dist[y][x] + w;
                    q.add(new int[]{ny, nx});
                }
            }
        }

        System.out.println(dist[y2][x2] - 1);
    }

    static boolean inMap(int y, int x) {
        return 0 <= y && y < n && 0 <= x && x < m;
    }
}
