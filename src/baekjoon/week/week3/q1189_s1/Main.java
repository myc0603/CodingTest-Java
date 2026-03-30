package baekjoon.week.week3.q1189_s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int r, c, k;
    static char[][] map;

    static boolean[][] visited;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new char[r][c];
        visited = new boolean[r][c];

        for (int i = 0; i < r; ++i) {
            String line = br.readLine();
            for (int j = 0; j < c; ++j) {
                map[i][j] = line.charAt(j);
            }
        }

        // visited 처리하고 dfs 호출하는 형식이라 처음에도 그에 맞춰줘야 함
        visited[r - 1][0] = true;
        dfs(r - 1, 0, 1);

        System.out.println(ans);
    }

    static void dfs(int y, int x, int cnt) {
        if (cnt == k) {
            if (y == 0 && x == c - 1) ++ans;
            return;
        }

        for (int i = 0; i < 4; ++i) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if (!inMap(ny, nx) || map[ny][nx] == 'T' || visited[ny][nx]) continue;

            visited[ny][nx] = true;
            dfs(ny, nx, cnt + 1);
            visited[ny][nx] = false;
        }
    }

    static boolean inMap(int y, int x) {
        return 0 <= y && y < r && 0 <= x && x < c;
    }

}
