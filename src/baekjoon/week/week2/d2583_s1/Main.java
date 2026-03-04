package baekjoon.week.week2.d2583_s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int n, m, k;
    static int[][] a;
    static boolean[][] visited;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};
    static ArrayList<Integer> sizes = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        a = new int[n][m];
        visited = new boolean[n][m];

        for (int ii = 0; ii < k; ++ii) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            for (int i = y1; i < y2; ++i) {
                for (int j = x1; j < x2; ++j) {
                    ++a[i][j];
                }
            }
        }

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (a[i][j] == 0 && !visited[i][j]) {
                    ++cnt;
                    sizes.add(dfs(i, j));
                }
            }
        }

        sizes.sort(null);
        StringBuilder sb = new StringBuilder();
        sb.append(cnt).append('\n');
        for (Integer size : sizes) sb.append(size).append(' ');
        System.out.println(sb);
    }

    static int dfs(int y, int x) {
        visited[y][x] = true;
        
        int size = 1;
        for (int i = 0; i < 4; ++i) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (!inMap(ny, nx) || a[ny][nx] != 0 || visited[ny][nx]) continue;

            size += dfs(ny, nx);
        }
        return size;
    }

    static boolean inMap(int y, int x) {
        return 0 <= y && y < n && 0 <= x && x < m;
    }
}
