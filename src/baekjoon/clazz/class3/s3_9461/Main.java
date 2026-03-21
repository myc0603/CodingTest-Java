package baekjoon.clazz.class3.s3_9461;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int t;
    static long[] arr = new long[101];

    public static void main(String[] args) throws IOException {
        buildArr();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            sb.append(arr[n]).append('\n');
        }
        System.out.println(sb);
    }

    static void buildArr() {
        arr[1] = 1;
        arr[2] = 1;
        arr[3] = 1;
        arr[4] = 2;
        arr[5] = 2;

        for (int i = 6; i <= 100; ++i) arr[i] = arr[i - 1] + arr[i - 5];
    }
}
