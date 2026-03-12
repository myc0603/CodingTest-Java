package baekjoon.week.week3.m2529_s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int k;
    static char[] signs;
    static String min, max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());
        signs = new char[k];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            signs[i] = st.nextToken().charAt(0);
        }

        for (int i = 0; i <= 9; i++) {
            boolean found = dfsMin(0, String.valueOf(i), 1 << i);
            if (found) break;
        }

        for (int i = 9; i >= 0; --i) {
            boolean found = dfsMax(0, String.valueOf(i), 1 << i);
            if (found) break;
        }

        System.out.println(max);
        System.out.println(min);
    }

    static boolean dfsMin(int idx, String cur, int used) {
        if (idx == k) {
            min = cur;
            return true;
        }

        for (int i = 0; i <= 9; ++i) {
            if ((used & (1 << i)) != 0) continue;
            int last = cur.charAt(cur.length() - 1) - '0';
            if ((signs[idx] == '<' && last < i) || (signs[idx] == '>' && last > i)) {
                boolean found = dfsMin(idx + 1, cur + i, used | (1 << i));
                if (found) return true;
            }
        }

        return false;
    }

    static boolean dfsMax(int idx, String cur, int used) {
        if (idx == k) {
            max = cur;
            return true;
        }

        for (int i = 9; i >= 0; --i) {
            if ((used & (1 << i)) != 0) continue;
            int last = cur.charAt(cur.length() - 1) - '0';
            if ((signs[idx] == '<' && last < i) || (signs[idx] == '>' && last > i)) {
                boolean found = dfsMax(idx + 1, cur + i, used | (1 << i));
                if (found) return true;
            }
        }

        return false;
    }
}
