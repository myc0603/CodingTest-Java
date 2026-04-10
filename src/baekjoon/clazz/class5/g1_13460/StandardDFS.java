package baekjoon.clazz.class5.g1_13460;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 기존의 dfs(Main)의 발전된 버전 - gpt가 한거
public class StandardDFS {
    static int n, m;
    static char[][] board;
    static int answer = Integer.MAX_VALUE;

    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    static int[][][][] visited;

    static class MoveResult {
        int y, x, dist;
        boolean inHole;

        MoveResult(int y, int x, int dist, boolean inHole) {
            this.y = y;
            this.x = x;
            this.dist = dist;
            this.inHole = inHole;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new char[n][m];
        visited = new int[n][m][n][m];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                for (int k = 0; k < n; ++k) {
                    Arrays.fill(visited[i][j][k], Integer.MAX_VALUE);
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

        visited[ry0][rx0][by0][bx0] = 0;
        dfs(ry0, rx0, by0, bx0, 0);

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    static void dfs(int ry, int rx, int by, int bx, int depth) {
        if (depth >= answer) return;
        if (depth == 10) return;

        for (int dir = 0; dir < 4; ++dir) {
            MoveResult red = move(ry, rx, dir);
            MoveResult blue = move(by, bx, dir);

            if (blue.inHole) continue;
            if (red.inHole) {
                answer = Math.min(answer, depth + 1);
                continue;
            }

            // 겹침 처리
            if (red.y == blue.y && red.x == blue.x) {
                if (red.dist > blue.dist) {
                    red.y -= dy[dir];
                    red.x -= dx[dir];
                } else {
                    blue.y -= dy[dir];
                    blue.x -= dx[dir];
                }
            }

            if (visited[red.y][red.x][blue.y][blue.x] <= depth + 1) continue;

            visited[red.y][red.x][blue.y][blue.x] = depth + 1;
            dfs(red.y, red.x, blue.y, blue.x, depth + 1);

        }
    }

    static MoveResult move(int sy, int sx, int dir) {
        int y = sy, x = sx;
        int dist = 0;
        while (true) {
            int ny = y + dy[dir];
            int nx = x + dx[dir];

            if (board[ny][nx] == '#') break;

            y = ny;
            x = nx;
            ++dist;

            if (board[y][x] == 'O') return new MoveResult(y, x, dist, true);
        }

        return new MoveResult(y, x, dist, false);
    }
}
