package baekjoon.week.week2.c2468_s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    // maxCnt - 최소 1개
    static int n, minH = 100, maxH = 1, maxCnt = 1;
    static int[][] a;
    static boolean[][] visited;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        a = new int[n][n];
        visited = new boolean[n][n];

        for (int i = 0; i < n; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                a[i][j] = Integer.parseInt(st.nextToken());
                minH = Math.min(minH, a[i][j]);
                maxH = Math.max(maxH, a[i][j]);
            }
        }

        for (int rain = minH; rain < maxH; ++rain) {
            int cnt = 0;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (a[i][j] > rain && !visited[i][j]) {
                        dfs(i, j, rain);
                        ++cnt;
                    }
                }
            }
            visited = new boolean[n][n];

            maxCnt = Math.max(cnt, maxCnt);
        }

        System.out.println(maxCnt);
    }

    static void dfs(int y, int x, int rain) {
        visited[y][x] = true;

        for (int i = 0; i < 4; ++i) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if (!inMap(ny, nx)) continue;
            if (a[ny][nx] <= rain) continue;
            if (visited[ny][nx]) continue;

            dfs(ny, nx, rain);
        }
    }

    static boolean inMap(int y, int x) {
        return 0 <= y && y < n && 0 <= x && x < n;
    }
}
