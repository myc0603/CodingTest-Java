package baekjoon.clazz.class4.g4_9663;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Standard {
    static int n, cnt;
    static boolean[] col, diag1, diag2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        col = new boolean[n];
        diag1 = new boolean[2 * n - 1];
        diag2 = new boolean[2 * n - 1];

        dfs(0);

        System.out.println(cnt);
    }

    // row == depth
    static void dfs(int row) {
        if (row == n) {
            ++cnt;
            return;
        }

        for (int j = 0; j < n; j++) {
            int d1 = row + j;
            int d2 = row - j + n - 1;
            if (col[j] || diag1[d1] || diag2[d2]) continue;
            check(j, d1, d2, true);
            dfs(row + 1);
            check(j, d1, d2, false);
        }
    }

    static void check(int j, int d1, int d2, boolean isCheck) {
        col[j] = isCheck;
        diag1[d1] = isCheck;
        diag2[d2] = isCheck;
    }
}
