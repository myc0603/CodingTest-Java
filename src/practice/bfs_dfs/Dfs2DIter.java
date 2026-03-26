package practice.bfs_dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Dfs2DIter {
    static int n, m;
    static int[][] a;
    static boolean[][] visited;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        a = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; ++i) {
            String line = br.readLine().trim();
            for (int j = 0; j < m; ++j) a[i][j] = line.charAt(j) - '0';
        }

        int components = 0;
        int maxSize = 0;

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (a[i][j] == 1 && !visited[i][j]) {
                    ++components;
                    int size = dfsStack(i, j);
                    maxSize = Math.max(maxSize, size);
                }
            }
        }

        System.out.println(components);
        System.out.println(maxSize);
    }

    static int dfsStack(int sy, int sx) {
        int size = 0;

        Deque<int[]> stk = new ArrayDeque<>();
        stk.push(new int[]{sy, sx});
        visited[sy][sx] = true;

        while (!stk.isEmpty()) {
            int[] cur = stk.pop();
            int y = cur[0], x = cur[1];

            // 방문 로직
            ++size;

            for (int i = 0; i < 4; ++i) {
                int ny = y + dy[i];
                int nx = x + dx[i];

                if (!inMap(ny, nx)) continue;
                if (a[ny][nx] == 0) continue;
                if (visited[ny][nx]) continue;

                visited[ny][nx] = true;
                stk.push(new int[]{ny, nx});
            }
        }

        return size;
    }

    static boolean inMap(int y, int x) {
        return 0 <= y && y < n && 0 <= x && x < m;
    }
}

/*
5 5
11000
11000
00100
00011
00011

7 7
1110000
1010100
1010100
1010100
0000111
0010101
0010111
*/