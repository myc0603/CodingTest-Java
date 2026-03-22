package baekjoon.clazz.class3.s3_11727;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        int[] f = new int[1001];
        f[1] = 1;
        f[2] = 3;
        for (int i = 3; i <= 1000; ++i) {
            f[i] = f[i - 1] + 2 * f[i - 2];
            f[i] %= 10007;
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        System.out.println(f[n]);
    }
}
