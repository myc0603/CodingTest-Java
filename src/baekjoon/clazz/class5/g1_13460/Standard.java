package baekjoon.clazz.class5.g1_13460;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

// GPT가 제시한 정석 풀이
// BFS
public class Standard {
    static int n, m;
    static char[][] board;
    static boolean[][][][] visited;

    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    static class State {
        int ry, rx, by, bx, depth;

        State(int ry, int rx, int by, int bx, int depth) {
            this.ry = ry;
            this.rx = rx;
            this.by = by;
            this.bx = bx;
            this.depth = depth;
        }
    }

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
        visited = new boolean[n][m][n][m];

        int ry0 = 0, rx0 = 0, by0 = 0, bx0 = 0;
        for (int i = 0; i < n; ++i) {
            String line = br.readLine();
            for (int j = 0; j < m; ++j) {
                char c = line.charAt(j);
                if (c == 'R') {
                    ry0 = i;
                    rx0 = j;
                    board[i][j] = '.';
                } else if (c == 'B') {
                    by0 = i;
                    bx0 = j;
                    board[i][j] = '.';
                } else {
                    board[i][j] = c;
                }
            }
        }

        System.out.println(bfs(ry0, rx0, by0, bx0));
    }

    static int bfs(int ry0, int rx0, int by0, int bx0) {
        ArrayDeque<State> q = new ArrayDeque<>();
        q.add(new State(ry0, rx0, by0, bx0, 0));
        visited[ry0][rx0][by0][bx0] = true;

        while (!q.isEmpty()) {
            State cur = q.poll();

            if (cur.depth == 10) continue;

            for (int dir = 0; dir < 4; ++dir) {
                MoveResult red = move(cur.ry, cur.rx, dir);
                MoveResult blue = move(cur.by, cur.bx, dir);

                if (blue.inHole) continue;
                if (red.inHole) return cur.depth + 1;

                if (red.y == blue.y && red.x == blue.x) {
                    if (red.dist > blue.dist) {
                        red.y -= dy[dir];
                        red.x -= dx[dir];
                    } else {
                        blue.y -= dy[dir];
                        blue.x -= dx[dir];
                    }
                }

                if (visited[red.y][red.x][blue.y][blue.x]) continue;
                visited[red.y][red.x][blue.y][blue.x] = true;
                q.add(new State(red.y, red.x, blue.y, blue.x, cur.depth + 1));
            }
        }

        return -1;
    }

    static MoveResult move(int sy, int sx, int dir) {
        int y = sy;
        int x = sx;
        int dist = 0;

        while (true) {
            int ny = y + dy[dir];
            int nx = x + dx[dir];

            if (board[ny][nx] == '#') break;

            y = ny;
            x = nx;
            dist++;

            // 구멍에 빠지면 stop
            if (board[y][x] == 'O') {
                return new MoveResult(y, x, dist, true);
            }
        }

        return new MoveResult(y, x, dist, false);
    }
}
