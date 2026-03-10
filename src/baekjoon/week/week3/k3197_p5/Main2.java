package baekjoon.week.week3.k3197_p5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main2 {
    static int n, m;
    static char[][] a;
    static boolean[][] visited;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};
    static int[] swan1, swan2;

    static ArrayDeque<int[]> swanQ;
    static ArrayDeque<int[]> swanNextQ = new ArrayDeque<>();
    static boolean[][] swanVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        a = new char[n][m];
        visited = new boolean[n][m];
        swanVisited = new boolean[n][m];

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

        swanNextQ.add(swan1);
        swanVisited[swan1[0]][swan1[1]] = true;

        int time = 0;
        while (true) {
            // 백조가 만날 수 있는 지 확인
            if (canMeet()) break;

            // 다음날 녹을 얼음 찾기
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

            // 녹이기 - 얘를 안 할수는 없음
            // toBeMelted 에는 swanNextQ에 없는 위치도 있음
            // 그래서 다음 swan bfs 에서  swanNextQ에 없지만 녹은 위치도 가게 하려면 녹여줘야 함
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
        swanQ = swanNextQ;
        swanNextQ = new ArrayDeque<>();

        while (!swanQ.isEmpty()) {
            int[] cur = swanQ.poll();
            int y = cur[0], x = cur[1];

            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];

                if (!inMap(ny, nx)) continue;
                if (ny == swan2[0] && nx == swan2[1]) return true;
                if (swanVisited[ny][nx]) continue;
                swanVisited[ny][nx] = true;
                if (a[ny][nx] == 'X') swanNextQ.add(new int[]{ny, nx});
                else swanQ.add(new int[]{ny, nx});
            }
        }

        return false;
    }
}
