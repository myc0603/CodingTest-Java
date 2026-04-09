package baekjoon.clazz.class5.g1_13460;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

// GPT가 제기한 BFS로 시도한 풀이
public class BFS {
    static int n, m;
    static char[][] board;

    static int ry0, rx0, by0, bx0;
    static int[][][][] visited;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new char[n][m];
        visited = new int[n][m][n][m];

        for (int i = 0; i < n; ++i) {
            String line = br.readLine();
            for (int j = 0; j < m; ++j) {
                board[i][j] = line.charAt(j);
                if (board[i][j] == 'R') {
                    ry0 = i;
                    rx0 = j;
                    board[i][j] = '.';
                } else if (board[i][j] == 'B') {
                    by0 = i;
                    bx0 = j;
                    board[i][j] = '.';
                }
            }
        }

        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{ry0, rx0, by0, bx0});
        visited[ry0][rx0][by0][bx0] = 1;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int ry = cur[0], rx = cur[1];
            int by = cur[2], bx = cur[3];

            if (board[by][bx] == 'O') continue;
            if (board[ry][rx] == 'O') {
                System.out.println(visited[ry][rx][by][bx] - 1);
                return;
            }
            if (visited[ry][rx][by][bx] - 1 >= 10) {
                continue;
//                System.out.println(-1);
//                return;
            }

            for (int i = 0; i < 4; ++i) {
                int[] nr = nextPos(ry, rx, i);
                int[] nb = nextPos(by, bx, i);
                if (nr[0] == nb[0] && nr[1] == nb[1] && board[nr[0]][nr[1]] != 'O') {
                    if (isRedFirst(ry, rx, by, bx, i)) {
                        nb[0] -= dy[i];
                        nb[1] -= dx[i];
                    } else {
                        nr[0] -= dy[i];
                        nr[1] -= dx[i];
                    }
                }

                if (visited[nr[0]][nr[1]][nb[0]][nb[1]] != 0) continue;
                visited[nr[0]][nr[1]][nb[0]][nb[1]] = visited[ry][rx][by][bx] + 1;
                q.add(new int[]{nr[0], nr[1], nb[0], nb[1]});
            }
        }

        System.out.println(-1);
    }

    static boolean isRedFirst(int ry, int rx, int by, int bx, int dir) {
        return (dy[dir] * ry >= dy[dir] * by) && (dx[dir] * rx >= dx[dir] * bx);
    }

    static int[] nextPos(int y, int x, int dir) {
        while (board[y][x] == '.') {
            y = y + dy[dir];
            x = x + dx[dir];
        }
        if (board[y][x] != 'O') {
            y -= dy[dir];
            x -= dx[dir];
        }
        return new int[]{y, x};
    }
}
