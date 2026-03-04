package baekjoon.gpt.perm_and_comb.g2_1256;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Standard {
    static final long INF = 1_000_000_000L + 1;
    static int n, m;
    static long k;
    static long[][] nCr = new long[201][201];

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Long.parseLong(st.nextToken());

        buildNCR();

        if (nCr[n + m][n] < k) {
            System.out.println(-1);
            return;
        }

        int cnt = 0;
        while (n != 0 && m != 0) {
            if (k <= nCr[n + m - 1][m]) {
                sb.append('a');
                --n;
            } else {
                sb.append('z');
                k -= nCr[n + m - 1][m];
                --m;
            }
        }

        for (int i = 0; i < n; ++i) sb.append('a');
        for (int i = 0; i < m; ++i) sb.append('z');
        System.out.println(sb);
    }

    private static void buildNCR() {
        for (int n = 0; n <= 200; ++n) {
            nCr[n][0] = 1;
            nCr[n][n] = 1;
            for (int r = 1; r < n; ++r) {
                nCr[n][r] = Math.min(INF, nCr[n - 1][r - 1] + nCr[n - 1][r]);
            }
        }
    }
}
