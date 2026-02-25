package baekjoon.week.week1.l1940_s4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TwoPointer {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int[] a = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; ++i) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(a);

        int cnt = 0;

        int l = 0;
        int r = n - 1;
        while (l < r) {
            if (a[l] + a[r] < m) ++l;
            else if (a[l] + a[r] > m) --r;
            else {
                ++cnt;
                ++l;
            }
        }

        System.out.println(cnt);
    }
}
