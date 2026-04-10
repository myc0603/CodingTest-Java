package baekjoon.clazz.class5.g1_13460;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 기존의 dfs(Main)의 발전된 버전 - 내가 한거
public class DFS {
    static int n, m;
    static char[][] board;

    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    static int[][][][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new char[n][m];
        dp = new int[n][m][n][m][11];
        for (int i1 = 0; i1 < n; ++i1) {
            for (int j1 = 0; j1 < m; ++j1) {
                for (int i2 = 0; i2 < n; ++i2) {
                    for (int j2 = 0; j2 < m; ++j2) {
                        Arrays.fill(dp[i1][j1][i2][j2], -1);
                    }
                }
            }
        }

        int ry0 = 0, rx0 = 0, by0 = 0, bx0 = 0;
        for (int i = 0; i < n; ++i) {
            String line = br.readLine();
            for (int j = 0; j < m; ++j) {
                board[i][j] = line.charAt(j);
                if (board[i][j] == 'R') {
                    board[i][j] = '.';
                    ry0 = i;
                    rx0 = j;
                } else if (board[i][j] == 'B') {
                    board[i][j] = '.';
                    by0 = i;
                    bx0 = j;
                }
            }
        }

        int ans = dfs(ry0, rx0, by0, bx0, 0);
        System.out.println(ans > 10 ? -1 : ans);
    }

    static int dfs(int ry, int rx, int by, int bx, int depth) {
        if (depth >= 10) return 100;

        if (dp[ry][rx][by][bx][depth] != -1) return dp[ry][rx][by][bx][depth];

        int ret = 100;
        for (int dir = 0; dir < 4; ++dir) {
            int[] red = move(ry, rx, dir);
            int[] blue = move(by, bx, dir);

            if (board[blue[0]][blue[1]] == 'O') continue;
            if (board[red[0]][red[1]] == 'O') return depth + 1;

            if (red[0] == blue[0] && red[1] == blue[1]) {
                if (red[2] > blue[2]) {
                    red[0] -= dy[dir];
                    red[1] -= dx[dir];
                } else {
                    blue[0] -= dy[dir];
                    blue[1] -= dx[dir];
                }
            }

            ret = Math.min(ret, dfs(red[0], red[1], blue[0], blue[1], depth + 1));
        }

        return dp[ry][rx][by][bx][depth] = ret;
    }

    // return: [y, x, dist]
    static int[] move(int sy, int sx, int dir) {
        int y = sy, x = sx;
        int dist = 0;
        while (true) {
            int ny = y + dy[dir];
            int nx = x + dx[dir];

            if (board[ny][nx] == '#') break;

            y = ny;
            x = nx;
            ++dist;

            if (board[y][x] == 'O') break;
        }

        return new int[]{y, x, dist};
    }
}
