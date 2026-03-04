package baekjoon.week.week2.p14502_g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int n, m, maxArea, possibleArea;
    static int[][] a;
    static boolean[][] visited;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};
    static ArrayList<int[]> blanks = new ArrayList<>();
    static ArrayList<int[]> viruses = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        a = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; ++j) {
                a[i][j] = Integer.parseInt(st.nextToken());
                if (a[i][j] == 0) {
                    blanks.add(new int[]{i, j});
                    ++possibleArea;
                } else if (a[i][j] == 2) {
                    viruses.add(new int[]{i, j});
                    ++possibleArea;
                }
            }
        }
        possibleArea -= 3;

        for (int i = 0; i < blanks.size(); ++i) {
            for (int j = i + 1; j < blanks.size(); j++) {
                for (int k = j + 1; k < blanks.size(); k++) {
                    // 벽을 세워
                    a[blanks.get(i)[0]][blanks.get(i)[1]] = 1;
                    a[blanks.get(j)[0]][blanks.get(j)[1]] = 1;
                    a[blanks.get(k)[0]][blanks.get(k)[1]] = 1;

                    // 안전 영역 넓이 세
                    int area = calc();
                    maxArea = Math.max(area, maxArea);

                    // 벽 지워
                    a[blanks.get(i)[0]][blanks.get(i)[1]] = 0;
                    a[blanks.get(j)[0]][blanks.get(j)[1]] = 0;
                    a[blanks.get(k)[0]][blanks.get(k)[1]] = 0;
                }
            }
        }

        System.out.println(maxArea);
    }

    static int calc() {
        int virusArea = 0;

        // 바이러스 전파되는 넓이 구해
        for (int[] virus : viruses) {
            int y = virus[0], x = virus[1];
            if (visited[y][x]) continue;
            virusArea += dfs(y, x);
        }

        // visited 초기화
        visited = new boolean[n][m];

        return possibleArea - virusArea;
    }

    static int dfs(int y, int x) {
        visited[y][x] = true;

        int area = 1;
        for (int i = 0; i < 4; ++i) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if (!inMap(ny, nx)) continue;
            if (a[ny][nx] == 1) continue;
            if (visited[ny][nx]) continue;

            area += dfs(ny, nx);
        }
        return area;
    }

    static boolean inMap(int y, int x) {
        return 0 <= y && y < n && 0 <= x & x < m;
    }
}
