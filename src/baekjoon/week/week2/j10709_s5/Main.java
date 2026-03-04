package baekjoon.week.week2.j10709_s5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[][] a;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        a = new int[n][m];

        for (int i = 0; i < n; ++i) {
            String line = br.readLine();
            Arrays.fill(a[i], 1000);

            boolean find = false;
            for (int j = 0; j < m; ++j) {
                char c = line.charAt(j);
                if (c == 'c') {
                    find = true;
                    for (int k = j; k < m; ++k) {
                        a[i][k] = Math.min(a[i][k], k - j);
                    }
                } else if (!find) a[i][j] = -1;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; j++) {
                sb.append(a[i][j]).append(' ');
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }
}
