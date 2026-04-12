package baekjoon.category.perm_and_comb.g2_1256;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2 {
    static final long INF = 1_000_000_000L + 1;
    static int n, m;
    static long k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Long.parseLong(st.nextToken());

        if (nCr(n + m, n) < k) {
            System.out.println(-1);
            return;
        }

        System.out.println(go(n, m, k));
    }

    private static String go(int a, int z, long k) {
        if (k == 1) {
            char[] arr = new char[a + z];
            for (int i = 0; i < a; ++i) arr[i] = 'a';
            for (int i = a; i < a + z; ++i) arr[i] = 'z';
            return String.valueOf(arr);
        }

        int i = 0;
        long tmp = nCr(z - 1, 0);
        while (k > tmp) {
            k -= tmp;
            ++i;
            tmp = nCr(z - 1 + i, i);
        }

        char[] aaa = new char[a - i + 1];
        for (int j = 0; j < a - i; ++j) aaa[j] = 'a';
        aaa[a - i] = 'z';

        return String.valueOf(aaa) + go(i, z - 1, k);
    }

    private static long nCr(int n, int r) {
        if (r > n / 2) return nCr(n, n - r);

        long result = 1;
        for (int i = n - r + 1; i <= n; ++i) {
            result *= i;
            result /= i - (n - r);
            if (result > INF) return INF;
        }
        return result;
    }
}
