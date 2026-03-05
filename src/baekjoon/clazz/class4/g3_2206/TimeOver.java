package baekjoon.clazz.class4.g3_2206;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class TimeOver {
    static int n, m, minDist = 10000000;
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
                a[i][j] = line.charAt(j) - '0';
            }
        }

        bfs();

        if (dist[n - 1][m - 1] != 0) minDist = Math.min(minDist, dist[n - 1][m - 1]);
        if (minDist == 10000000) minDist = -1;
        System.out.println(minDist);
    }

    static void bfs() {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        dist[0][0] = 1;
        q.add(0);

        while (!q.isEmpty()) {
            int cur = q.poll();
            int y = cur / m;
            int x = cur % m;

            for (int i = 0; i < 4; ++i) {
                int ny = y + dy[i];
                int nx = x + dx[i];
                if (!inMap(ny, nx)) continue;
                if (a[ny][nx] == 1) {
                    int d = bfsFromWall(ny, nx);
                    if (d > 0) minDist = Math.min(dist[y][x] + d, minDist);
                    continue;
                }
                if (dist[ny][nx] > 0) continue;

                dist[ny][nx] = dist[y][x] + 1;
                q.add(ny * m + nx);
            }
        }
    }

    static int bfsFromWall(int sy, int sx) {
        int[][] d = new int[n][m];
        ArrayDeque<Integer> q = new ArrayDeque<>();
        d[sy][sx] = 1;
        q.add(sy * m + sx);

        while (!q.isEmpty()) {
            int cur = q.poll();
            int y = cur / m;
            int x = cur % m;

            for (int i = 0; i < 4; ++i) {
                int ny = y + dy[i];
                int nx = x + dx[i];
                if (!inMap(ny, nx)) continue;
                if (a[ny][nx] == 1) continue;
                // dist - 이 함수가 호출되기 전까지 출발점(벽)에 도달할 때까지 방문한 노드는 값 > 0
                if (dist[ny][nx] > 0 || d[ny][nx] > 0) continue;

                d[ny][nx] = d[y][x] + 1;
                q.add(ny * m + nx);
            }
        }

        return d[n - 1][m - 1];
    }

    static boolean inMap(int y, int x) {
        return 0 <= y && y < n && 0 <= x && x < m;
    }
}
