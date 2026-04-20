package baekjoon.category.impl.g4_2573;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Standard {
    static int n, m;
    static int[][] map;
    static ArrayList<int[]> ices = new ArrayList<>();

    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; ++j) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] != 0) ices.add(new int[]{i, j});
            }
        }

        int year = 0;
        while (true) {
            year++;
            melt();
            updateIces();

            if (ices.isEmpty() || isSeparated()) break;
        }

        if (ices.isEmpty()) System.out.println(0);
        else System.out.println(year);
    }

    static void melt() {
        int[][] cnt = new int[n][m];

        for (int[] ice : ices) {
            int y = ice[0], x = ice[1];

            for (int i = 0; i < 4; ++i) {
                int ny = y + dy[i];
                int nx = x + dx[i];

                if (!inMap(ny, nx)) continue;
                if (map[ny][nx] == 0) cnt[y][x]++;
            }
        }

        for (int[] ice : ices) {
            int y = ice[0], x = ice[1];

            map[y][x] = Math.max(0, map[y][x] - cnt[y][x]);
        }
    }

    static void updateIces() {
        ArrayList<int[]> newIces = new ArrayList<>();
        for (int[] ice : ices) {
            int y = ice[0], x = ice[1];
            if (map[y][x] == 0) continue;

            newIces.add(ice);
        }

        ices = newIces;
    }

    static boolean isSeparated() {
        int cnt = 0;
        ArrayDeque<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][m];

        int[] ice = ices.get(0);
        q.add(ice);
        visited[ice[0]][ice[1]] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int y = cur[0], x = cur[1];
            cnt++;

            for (int i = 0; i < 4; ++i) {
                int ny = y + dy[i];
                int nx = x + dx[i];

                if (!inMap(ny, nx) || visited[ny][nx] || map[ny][nx] == 0) continue;

                q.add(new int[]{ny, nx});
                visited[ny][nx] = true;
            }
        }

        return cnt != ices.size();
    }

    static boolean inMap(int y, int x) {
        return 0 <= y && y < n && 0 <= x && x < m;
    }
}
