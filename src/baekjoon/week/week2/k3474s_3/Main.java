package baekjoon.week.week2.k3474s_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int T, N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            sb.append(cnt(N)).append('\n');
        }
        System.out.println(sb);
    }

    static int cnt(int n) {
        int cnt = 0;
        for (int d = n / 5; d > 0; d /= 5) {
            cnt += d;
        }
        return cnt;
    }
}
