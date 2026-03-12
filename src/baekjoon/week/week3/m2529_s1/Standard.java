package baekjoon.week.week3.m2529_s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Standard {
    static int k;
    static char[] signs;
    static boolean[] used = new boolean[10];
    static char[] selected;
    static String min, max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        k = Integer.parseInt(br.readLine());
        signs = new char[k];
        selected = new char[k + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            signs[i] = st.nextToken().charAt(0);
        }

        dfsMax(0);
        Arrays.fill(used, false);
        dfsMin(0);

        System.out.println(max);
        System.out.println(min);
    }

    static boolean dfsMax(int depth) {
        if (depth == k + 1) {
            max = new String(selected);
            return true;
        }

        for (int i = 9; i >= 0; --i) {
            if (used[i]) continue;
            if (depth == 0 || isOk(selected[depth - 1] - '0', signs[depth - 1], i)) {
                selected[depth] = (char) (i + '0');
                used[i] = true;
                if (dfsMax(depth + 1)) return true;
                used[i] = false;
            }

        }

        return false;
    }

    static boolean dfsMin(int depth) {
        if (depth == k + 1) {
            min = new String(selected);
            return true;
        }

        for (int i = 0; i <= 9; i++) {
            if (used[i]) continue;
            if (depth == 0 || isOk(selected[depth - 1] - '0', signs[depth - 1], i)) {
                selected[depth] = (char) (i + '0');
                used[i] = true;
                if (dfsMin(depth + 1)) return true;
                used[i] = false;
            }
        }

        return false;
    }

    static boolean isOk(int x, char sign, int y) {
        if (sign == '>') return x > y;
        return x < y;
    }
}
