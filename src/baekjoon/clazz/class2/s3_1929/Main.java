package baekjoon.clazz.class2.s3_1929;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static boolean[] isPrime;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);
        isPrime[1] = false;

        for (int i = 2; i * i <= n; ++i) {
            if (!isPrime[i]) continue;
            for (int j = i * i; j <= n; j += i) {
                if (j % i == 0) isPrime[j] = false;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = m; i <= n; ++i) {
            if (isPrime[i]) sb.append(i).append('\n');
        }
        System.out.println(sb);
    }
}
