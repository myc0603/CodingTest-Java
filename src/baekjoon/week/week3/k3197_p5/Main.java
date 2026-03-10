package baekjoon.week.week3.k3197_p5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

// 시간초과
public class Main {
    static int n, m;
    static char[][] a;
    static boolean[][] visited;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};
    static int[] swan1, swan2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        a = new char[n][m];
        visited = new boolean[n][m];

        ArrayDeque<int[]> q = new ArrayDeque<>();
        for (int i = 0; i < n; ++i) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                a[i][j] =line.charAt(j);
                if (a[i][j] == '.' || a[i][j] == 'L') {
                    q.add(new int[]{i, j});
                    visited[i][j] = true;
                }
                if (a[i][j] == 'L') {
                    if (swan1 == null) swan1 = new int[]{i, j};
                    else swan2 = new int[]{i, j};
                }
            }
        }

        int time = 0;
        while (true) {
            // 백조가 만날 수 있는 지 확인
            if (canMeet()) break;

            // 녹일 얼음 찾기
            ArrayDeque<int[]> toBeMelted = new ArrayDeque<>();
            while (!q.isEmpty()) {
                int[] cur = q.poll();
                int y = cur[0], x = cur[1];

                for (int i = 0; i < 4; i++) {
                    int ny = y + dy[i];
                    int nx = x + dx[i];

                    if (!inMap(ny, nx) || visited[ny][nx]) continue;
                    visited[ny][nx] = true;
                    if (a[ny][nx] == 'X') {
                        toBeMelted.add(new int[]{ny, nx});
                    } else if (a[ny][nx] == '.') {
                        q.add(new int[]{ny, nx});
                    }
                }
            }

            // 녹이기
            for (int[] ice : toBeMelted) {
                a[ice[0]][ice[1]] = '.';
            }

            // 다음 bfs를 위한 q 갱신 및 time 갱신
            q = toBeMelted;
            ++time;
        }

        System.out.println(time);
    }

    static boolean inMap(int y, int x) {
        return 0 <= y && y < n && 0 <= x && x < m;
    }

    static boolean canMeet() {
        boolean[][] swanVisited = new boolean[n][m];
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.add(swan1);
        swanVisited[swan1[0]][swan1[1]] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int y = cur[0], x = cur[1];

            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];

                if (!inMap(ny, nx) || a[ny][nx] == 'X' || swanVisited[ny][nx]) continue;
                if (ny == swan2[0] && nx == swan2[1]) return true;
                swanVisited[ny][nx] = true;
                q.add(new int[]{ny, nx});
            }
        }

        return false;
    }
}
