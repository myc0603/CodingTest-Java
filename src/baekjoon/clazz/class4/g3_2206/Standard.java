package baekjoon.clazz.class4.g3_2206;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Standard {
    static int n, m;
    static int[][] a;
    static int[][][] dist;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        a = new int[n][m];
        dist = new int[n][m][2];

        for (int i = 0; i < n; ++i) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                a[i][j] = line.charAt(j) - '0';
            }
        }

        System.out.println(bfs());
    }

    static int bfs() {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        dist[0][0][0] = 1;
        q.add(new int[]{0, 0, 0});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int y = cur[0], x = cur[1], broken = cur[2];

            // 도착하면 바로 리턴 (BFS 라서 처음 도착이 최단 거리)
            if (y == n - 1 && x == m - 1) return dist[y][x][broken];

            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];
                if (ny < 0 || n <= ny || nx < 0 || m <= nx) continue;

                if (a[ny][nx] == 0) {
                    // 그냥 이동
                    if (dist[ny][nx][broken] != 0) continue;
                    dist[ny][nx][broken] = dist[y][x][broken] + 1;
                    q.add(new int[]{ny, nx, broken});
                } else {
                    // 벽
                    if (broken == 1) continue;
                    if (dist[ny][nx][1] != 0) continue;
                    dist[ny][nx][1] = dist[y][x][0] + 1;
                    q.add(new int[]{ny, nx, 1});
                }
            }
        }

        // 도착 못 했으면 -1 리턴
        return -1;
    }
}
