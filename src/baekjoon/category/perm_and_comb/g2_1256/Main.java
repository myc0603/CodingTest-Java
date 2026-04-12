package baekjoon.category.perm_and_comb.g2_1256;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static long k, cnt;
    static char[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Long.parseLong(st.nextToken());

        arr = new char[n + m];
        for (int i = 0; i < n; ++i) arr[i] = 'a';
        for (int i = n; i < n + m; ++i) arr[i] = 'z';

        long ncr = n > m ? nCr(n + m, m) : nCr(n + m, n);
        if (ncr < k) {
            System.out.println(-1);
            return;
        }

        do {
            ++cnt;
            if (k == cnt) System.out.println(String.valueOf(arr));
        } while (nextPermutation());
    }

    private static boolean nextPermutation() {
        int i = arr.length - 1;
        while (i > 0 && arr[i - 1] >= arr[i]) --i;
        if (i == 0) return false;

        int j = arr.length - 1;
        while (arr[i - 1] >= arr[j]) --j;
        swap(i - 1, j);

        int k = arr.length - 1;
        while (i < k) swap(i++, k--);
        return true;
    }

    private static void swap(int i, int j) {
        char t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    private static long nCr(int n, int r) {
        long result = 1;
        for (int i = n - r + 1; i <= n; ++i) {
            result *= i;
            result /= i - (n - r);
        }
        return result;
    }
}
