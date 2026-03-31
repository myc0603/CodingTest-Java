package baekjoon.clazz.class6.g2_15824;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main2 {
    static int n;
    static long[] arr;

    static long[] pow2;
    static long MOD = 1000000007;

    static long ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        arr = new long[n];
        pow2 = new long[n];
        buildPow2();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; ++i) {
            arr[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(arr);

        for (int i = 0; i < n; ++i) {
            long powDiff = pow2[i] - pow2[n - 1 - i];
            if (powDiff < 0) powDiff += MOD;
            ans += powDiff * arr[i] % MOD;
            ans %= MOD;
        }

        System.out.println(ans);
    }

    static void buildPow2() {
        long base = 2;

        long cur = 1;
        for (int i = 0; i < n; ++i) {
            pow2[i] = cur;
            cur = cur * base % MOD;
        }
    }
}
