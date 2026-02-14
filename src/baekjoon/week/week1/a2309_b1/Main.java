package baekjoon.week.week1.a2309_b1;

import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] a = new int[9];
        int sum = 0;

        for (int i = 0; i < 9; ++i) {
            a[i] = Integer.parseInt(br.readLine());
            sum += a[i];
        }
        br.close();

        Arrays.sort(a);

        for (int i = 0; i < 9; ++i) {
            for (int j = i + 1; j < 9; ++j) {
                if (sum - a[i] - a[j] == 100) {
                    // answer
                    StringBuilder sb = new StringBuilder();
                    for (int k : a) {
                        if (k == a[i] || k == a[j]) continue;
                        sb.append(k).append('\n');
                    }

                    System.out.println(sb);
                    return;
                }
            }
        }
    }
}
