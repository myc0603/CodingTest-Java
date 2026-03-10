package baekjoon.week.week3.j14497_g4;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int n, m, x1, y1, x2, y2;
    static int[][] a;
    static boolean[][] visited;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        a = new int[n][m];
        visited = new boolean[n][m];

        st = new StringTokenizer(br.readLine());
        y1 = Integer.parseInt(st.nextToken());
        x1 = Integer.parseInt(st.nextToken());
        y2 = Integer.parseInt(st.nextToken());
        x2 = Integer.parseInt(st.nextToken());
        --y1; --x1; --y2; --x2;

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                char c = line.charAt(j);
                if (c == '0') a[i][j] = 0;
                else if (c == '1' || c == '#') a[i][j] =1;
            }
        }

        int cnt = 1;
        ArrayList<int[]> toBeZero = new ArrayList<>(List.of(new int[]{y1, x1}));
        while (true) {
            toBeZero = bfs(toBeZero);
            for (int[] pos : toBeZero) {
                int y = pos[0], x = pos[1];
                if (y == y2 && x == x2) {
                    System.out.println(cnt);
                    return;
                }
                a[y][x] = 0;
            }
            ++cnt;
        }
    }

    static ArrayList<int[]> bfs(ArrayList<int[]> sources) {
        ArrayDeque<int[]> q = new ArrayDeque<>(sources);
        for (int[] s : sources) {
            visited[s[0]][s[1]] = true;
        }

        ArrayList<int[]> toBeZero = new ArrayList<>();
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int y = cur[0], x = cur[1];

            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];

                if (!inMap(ny, nx)) continue;
                if (visited[ny][nx]) continue;

                visited[ny][nx] = true;

                // 이것도 visited 처리 해야됨
                if (a[ny][nx] == 1) toBeZero.add(new int[]{ny, nx});
                else q.add(new int[]{ny, nx});
            }
        }

        return toBeZero;
    }

    static boolean inMap(int y, int x) {
        return 0 <= y && y < n && 0 <= x && x < m;
    }
}
