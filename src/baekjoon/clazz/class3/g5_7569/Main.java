package baekjoon.clazz.class3.g5_7569;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m, h, maxDays, cnt;
    static int[][][] a, days;
    static int[] dz = {1, -1, 0, 0, 0, 0};
    static int[] dy = {0, 0, 1, -1, 0, 0};
    static int[] dx = {0, 0, 0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        a = new int[n][m][h];
        days = new int[n][m][h];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) Arrays.fill(days[i][j], Integer.MAX_VALUE);
        }

        Deque<int[]> dq = new ArrayDeque<>();
        for (int k = 0; k < h; ++k) {
            for (int i = 0; i < n; ++i) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < m; ++j) {
                    a[i][j][k] = Integer.parseInt(st.nextToken());
                    if (a[i][j][k] == 1) {
                        dq.add(new int[]{i, j, k});
                        days[i][j][k] = 0;
                    } else if (a[i][j][k] == 0) ++cnt;
                }
            }
        }

        while (!dq.isEmpty()) {
            int[] cur = dq.poll();
            int y = cur[0], x = cur[1], z = cur[2];

            for (int i = 0; i < 6; ++i) {
                int ny = y + dy[i];
                int nx = x + dx[i];
                int nz = z + dz[i];

                if (!inMap(ny, nx, nz)) continue;
                if (a[ny][nx][nz] == -1) continue;

                int w = a[ny][nx][nz] == 1 ? 0 : 1;
                if (days[ny][nx][nz] > days[y][x][z] + w) {
                    if (days[ny][nx][nz] == Integer.MAX_VALUE && a[ny][nx][nz] == 0) --cnt;
                    days[ny][nx][nz] = days[y][x][z] + w;
                    maxDays = Math.max(maxDays, days[ny][nx][nz]);

                    if (w == 1) dq.addLast(new int[]{ny, nx, nz});
                    else dq.addFirst(new int[]{ny, nx, nz});
                }
            }
        }

        if (cnt != 0) System.out.println(-1);
        else System.out.println(maxDays);
    }

    static boolean inMap(int y, int x, int z) {
        return 0 <= z && z < h && 0 <= y && y < n && 0 <= x && x < m;
    }
}
