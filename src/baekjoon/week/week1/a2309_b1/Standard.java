package baekjoon.week.week1.a2309_b1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Standard {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] a = new int[9];
        int sum = 0;

        for (int i = 0; i < 9; ++i) {
            a[i] = Integer.parseInt(br.readLine());
            sum += a[i];
        }

        int x = -1, y = -1;

        for (int i = 0; i < 9; ++i) {
            for (int j = i + 1; j < 9; ++j) {
                if (sum - a[i] - a[j] == 100) {
                    x = i;
                    y = j;
                }
            }
            if (x != -1) break;
        }

        int[] ans = new int[7];
        int idx = 0;
        for (int i = 0; i < 9; ++i) {
            if (i == x || i == y) continue;
            ans[idx++] = a[i];
        }

        Arrays.sort(ans);

        StringBuilder sb = new StringBuilder();
        for (int n : ans) sb.append(n).append('\n');
        System.out.println(sb.toString());
    }
}
