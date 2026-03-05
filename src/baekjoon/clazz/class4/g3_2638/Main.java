package baekjoon.clazz.class4.g3_2638;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int n, m, chzCnt;
    static int[][] a, touchCnt;
    static boolean[][] visited;
    static ArrayList<int[]> toBeMelted = new ArrayList<>();
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        a = new int[n][m];
        touchCnt = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                a[i][j] = Integer.parseInt(st.nextToken());
                if (a[i][j] == 1) ++chzCnt;
            }
        }

        int time = 0;
        while (chzCnt > 0) {
            ++time;

            // bfs/dfs 쫙 돌면서 touchCnt 갱신, 2 이상이면 toBeMelted에 추가
            bfs(0, 0);

            // 치즈 녹여
            for (int[] chz : toBeMelted) {
                int y = chz[0], x = chz[1];
                a[y][x] = 0;
            }
            chzCnt -= toBeMelted.size();

            // visited, toBeMelted 초기화
            visited = new boolean[n][m];
            toBeMelted = new ArrayList<>();
            touchCnt = new int[n][m];
        }

        System.out.println(time);
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
                if (a[ny][nx] == 1) {
                    ++touchCnt[ny][nx];
                    if (touchCnt[ny][nx] == 2) toBeMelted.add(new int[]{ny, nx});
                    continue;
                }
                if (visited[ny][nx]) continue;

                q.add(new int[]{ny, nx});
                visited[ny][nx] = true;
            }
        }
    }

    static boolean inMap(int y, int x) {
        return 0 <= y && y < n && 0 <= x && x < m;
    }
}
