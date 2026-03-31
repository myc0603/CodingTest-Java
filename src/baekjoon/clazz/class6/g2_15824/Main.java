package baekjoon.clazz.class6.g2_15824;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static long[] arr;

    static long[] pow2;
    static int MOD = 1000000007;

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
            for (int j = i + 1; j < n; ++j) {
                long diff = (arr[j] - arr[i]) % MOD;
                ans += (diff * pow2[j - i - 1]) % MOD;
                ans %= MOD;
            }
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
