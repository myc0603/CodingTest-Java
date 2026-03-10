package baekjoon.week.week3.j14497_g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main2 {
    static int n, m, x1, y1, x2, y2;
    static int[][] a;
    static boolean[][] visited;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        a = new int[n][m];
        visited = new boolean[n][m];

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

        int cnt = 1;
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{y1, x1});

        while (true) {
            ArrayDeque<int[]> temp = new ArrayDeque<>();
            while (!q.isEmpty()) {
                int[] cur = q.poll();
                int y = cur[0], x = cur[1];

                for (int i = 0; i < 4; i++) {
                    int ny = y + dy[i];
                    int nx = x + dx[i];

                    if (!inMap(ny, nx)) continue;
                    if (a[ny][nx] == '#') {
                        System.out.println(cnt);
                        return;
                    }
                    if (visited[ny][nx]) continue;
                    visited[ny][nx] = true;
                    if (a[ny][nx] == '1') temp.add(new int[]{ny, nx});
                    else q.add(new int[]{ny, nx});
                }
            }

            q = temp;
            ++cnt;
        }
    }

    static boolean inMap(int y, int x) {
        return 0 <= y && y < n && 0 <= x && x < m;
    }
}
