package baekjoon.clazz.class4.g3_2206;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int max = Integer.MAX_VALUE;
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
            for (int j = 0; j < m; j++) {
                Arrays.fill(dist[i][j], max);
            }
        }

        for (int i = 0; i < n; ++i) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                a[i][j] = line.charAt(j) - '0';
            }
        }

        bfs();

        if (dist[n - 1][m - 1][0] == max && dist[n - 1][m - 1][1] == max) {
            System.out.println(-1);
            return;
        }
        System.out.println(Math.min(dist[n - 1][m - 1][0], dist[n - 1][m - 1][1]));
    }

    static void bfs() {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        dist[0][0][0] = 1;
        q.add(new int[]{0, 0, 0});

        while (!q.isEmpty()) {
            int[] cur = q.pop();
            int y = cur[0], x = cur[1], wall = cur[2];
            for (int i = 0; i < 4; ++i) {
                int ny = y + dy[i];
                int nx = x + dx[i];

                if (!inMap(ny, nx)) continue;
                if (a[ny][nx] == 1 && wall == 0 && dist[ny][nx][1] == max) {
//                    if (dist[ny][nx][0] < dist[y][x][0] + 1) continue;
                    dist[ny][nx][1] = dist[y][x][0] + 1;
                    q.add(new int[]{ny, nx, 1});
                    continue;
                }
                if (a[ny][nx] == 1) continue;
                if (dist[ny][nx][wall] < max) continue;

                dist[ny][nx][wall] = dist[y][x][wall] + 1;
                q.add(new int[]{ny, nx, wall});
            }
        }
    }

    static boolean inMap(int y, int x) {
        return 0 <= y && y < n && 0 <= x && x < m;
    }
}
