package baekjoon.week.week2.q2636_g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m, time, chzCnt;
    static int[][] a;
    static boolean[][] visited;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};
    static ArrayList<int[]> toBeMelted = new ArrayList<>();

    static void printA() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; j++) {
                sb.append(a[i][j]).append(' ');
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        a = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                a[i][j] = Integer.parseInt(st.nextToken());
                if (a[i][j] == 1) ++chzCnt;
            }
        }

        while (true) {
            ++time;

            // toBeMelted 확인
//            dfs(0, 0);
            bfs(0, 0);

            // 마지막이면 break;
            if (chzCnt == toBeMelted.size()) break;

            // 치즈 녹여
            for (int[] chz : toBeMelted) {
                int y = chz[0], x = chz[1];
                a[y][x] = 0;
            }


            // chzCnt 감소
            chzCnt -= toBeMelted.size();

            // toBeMelted, visited 초기화
            toBeMelted = new ArrayList<>();
            visited = new boolean[n][m];
        }

        System.out.println(time);
        System.out.println(chzCnt);
    }

    static void dfs(int y, int x) {
        visited[y][x] = true;

        for (int i = 0; i < 4; ++i) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if (!inMap(ny, nx)) continue;
            if (a[ny][nx] == 1 && !visited[ny][nx]) { // 치즈
                toBeMelted.add(new int[]{ny, nx});
                visited[ny][nx] = true;
                continue;
            }
            if (visited[ny][nx]) continue;

            dfs(ny, nx);
        }
    }

    static void bfs(int sy, int sx) {
        Queue<int[]> q = new ArrayDeque<>();
        visited[sy][sx] = true;
        q.add(new int[]{sy, sx});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int y = cur[0], x = cur[1];

            for (int i = 0; i < 4; ++i) {
                int ny = y + dy[i];
                int nx = x + dx[i];

                if (!inMap(ny, nx)) continue;
                if (a[ny][nx] == 1 && !visited[ny][nx]) {
                    toBeMelted.add(new int[]{ny, nx});
                    visited[ny][nx] = true;
                    continue;
                }
                if (!visited[ny][nx]) {
                    visited[ny][nx] = true;
                    q.add(new int[]{ny, nx});
                }
            }
        }
    }

    static boolean inMap(int y, int x) {
        return 0 <= y && y < n && 0 <= x && x < m;
    }
}
