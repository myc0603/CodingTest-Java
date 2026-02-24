package baekjoon.week.week1.a2309_b1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class TwoPointer {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] a= new int[9];
        int sum = 0;
        for (int i = 0; i < 9; ++i) {
            a[i] = Integer.parseInt(br.readLine());
            sum += a[i];
        }

        Arrays.sort(a);

        int l = 0;
        int r = 8;

        while (l < r) {
            if (a[l] + a[r] < sum - 100) ++l;
            else if (a[l] + a[r] > sum - 100) --r;
            else break;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; ++i) {
            if (i == l || i == r) continue;
            sb.append(a[i]).append('\n');
        }
        System.out.println(sb);
    }
}
