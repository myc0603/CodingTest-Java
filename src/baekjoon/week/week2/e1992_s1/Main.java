package baekjoon.week.week2.e1992_s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int n;
    static int[][] a;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        a = new int[n][n];

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                a[i][j] = line.charAt(j) - '0';
            }
        }

        System.out.println(comp(0, 0, n));
    }

    static String comp(int y, int x, int size) {
        if (size == 1) {
            return String.valueOf(a[y][x]);
        }

        String c1 = comp(y, x, size / 2);
        String c2 = comp(y, x + size / 2, size / 2);
        String c3 = comp(y + size / 2, x, size / 2);
        String c4 = comp(y + size / 2, x + size / 2, size / 2);

        if (c1.length() == 1 && c1.equals(c2) && c2.equals(c3) && c3.equals(c4)) {
            return c1;
        }

        return '(' + c1 + c2 + c3 + c4 + ')';
    }

}
