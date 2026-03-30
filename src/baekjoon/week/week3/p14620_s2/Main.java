package baekjoon.week.week3.p14620_s2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] arr;
    static boolean[][] planted;

    static int[] dy = {1, -1, 0, 0, 0};
    static int[] dx = {0, 0, 1, -1, 0};

    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        arr = new int[n][n];
        planted = new boolean[n][n];

        StringTokenizer st;
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; ++j) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(1, 1, 0, 0);

        System.out.println(ans);
    }

    static void dfs(int si, int sj, int cnt, int cur) {
        if (cnt == 3) {
            ans = Math.min(ans, cur);
            return;
        }

        if (cur >= ans) return;

        for (int i = si; i < n - 1; ++i) {
            // i가 si + 1 일 때 부터는 j = 1부터 탐색해야 됨
            int startJ = i == si ? sj : 1;
            for (int j = startJ; j < n - 1; ++j) {
                if (!canPlant(i, j)) continue;

                cur += plant(i, j, true);
                dfs(i, j + 1, cnt + 1, cur);
                cur -= plant(i, j, false);
            }
        }
    }

    static boolean canPlant(int y, int x) {
        if (y < 1 || n - 1 <= y || x < 1 || n - 1 <= x) return false;
        for (int i = 0; i < 5; ++i) {
            if (planted[y + dy[i]][x + dx[i]]) return false;
        }
        return true;
    }

    static int plant(int y, int x, boolean isPlant) {
        int sum = 0;
        for (int i = 0; i < 5; ++i) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            planted[ny][nx] = isPlant;
            sum += arr[ny][nx];
        }

        return sum;
    }
}
