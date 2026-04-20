package baekjoon.category.impl.g4_2573;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] iceHeight;
    static int[][] adjWaterCnt;
    static ArrayList<int[]> ices = new ArrayList<>();

    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        iceHeight = new int[n][m];
        adjWaterCnt = new int[n][m];

        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; ++j) {
                iceHeight[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        update();

        int year = 0;
        do {
            year++;

            for (int[] ice : ices) {
                int y = ice[0], x = ice[1];
                iceHeight[y][x] = Math.max(0, iceHeight[y][x] - adjWaterCnt[y][x]);
            }

            update();
        } while (!isSeparated() && !ices.isEmpty());

        if (ices.isEmpty()) System.out.println(0);
        else System.out.println(year);
    }

    static void update() {
        ices.clear();
        for (int i = 0; i < n; ++i) Arrays.fill(adjWaterCnt[i], 0);

        for (int y = 0; y < n; ++y) {
            for (int x = 0; x < m; ++x) {
                if (iceHeight[y][x] != 0) {
                    ices.add(new int[]{y, x});
                    continue;
                }

                for (int d = 0; d < 4; ++d) {
                    int ny = y + dy[d];
                    int nx = x + dx[d];
                    if (!inMap(ny, nx)) continue;

                    adjWaterCnt[ny][nx]++;
                }
            }
        }
    }

    static boolean inMap(int y, int x) {
        return 0 <= y && y < n && 0 <= x && x < m;
    }

    static boolean isSeparated() {
        boolean[][] visited = new boolean[n][m];

        int cnt = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (iceHeight[i][j] == 0) continue;
                if (visited[i][j]) continue;
                if (cnt > 0) return true;
                cnt++;
                dfs(i, j, visited);
            }
        }

        return false;
    }

    static void dfs(int y, int x, boolean[][] visited) {
        visited[y][x] = true;

        for (int i = 0; i < 4; ++i) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if (!inMap(ny, nx) || visited[ny][nx] || iceHeight[ny][nx] == 0) continue;

            dfs(ny, nx, visited);
        }
    }
}
