package baekjoon.clazz.class4.g4_9663;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// time over
public class Main {
    static int n, cnt;
    static int[] a;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        a = new int[n * n];

        for (int i = 0; i < n * n; ++i) {
            check(i, 1);
            dfs(1, i + 1);
            uncheck(1);
        }

        System.out.println(cnt);
    }

    static void dfs(int depth, int start) {
        if (depth == n) {
            ++cnt;
            return;
        }

        for (int i = start; i < n * n; ++i) {
            if (a[i] != 0) continue;
            check(i, depth + 1);
            dfs(depth + 1, i + 1);
            uncheck(depth + 1);
        }
    }

    static void check(int idx, int mark) {
        // 대각선
        int y = idx / n, x = idx % n;
        while (y < n && x < n) {
            int i = y * n + x;
            if (a[i] == 0) a[i] = mark;
            ++y; ++x;
        }
        y = idx / n; x = idx % n;
        while (y < n && 0 <= x) {
            int i = y * n + x;
            if (a[i] == 0) a[i] = mark;
            ++y; --x;
        }

        // 가로
        y = idx / n; x = idx % n;
        while (x < n) {
            int i = y * n + x;
            if (a[i] == 0) a[i] = mark;
            ++x;
        }

        // 세로
        y = idx / n; x = idx % n;
        while (y < n) {
            int i = y * n + x;
            if (a[i] == 0) a[i] = mark;
            ++y;
        }
    }

    static void uncheck(int mark) {
        for (int i = 0; i < n * n; ++i) {
            if (a[i] == mark) a[i] = 0;
        }
    }
}
