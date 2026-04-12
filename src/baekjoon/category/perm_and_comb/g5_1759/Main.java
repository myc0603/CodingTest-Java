package baekjoon.category.perm_and_comb.g5_1759;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int r, n;
    static char[] chars;

    static char[] result;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        chars = new char[n];
        result = new char[r];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; ++i) {
            chars[i] = st.nextToken().charAt(0);
        }
        Arrays.sort(chars);

        comb(0, 0);

        System.out.println(sb);
    }

    private static void comb(int depth, int start) {
        if (depth == r) {
            if (isOk()) {
                sb.append(result).append('\n');
            }
            return;
        }

        for (int i = start; i < n; ++i) {
            result[depth] = chars[i];
            comb(depth + 1, i + 1);
        }
    }

    private static boolean isOk() {
        int vowelCnt = 0;
        for (int i = 0; i < r; ++i) {
            char c = result[i];
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') ++vowelCnt;
        }
        return vowelCnt >= 1 && (r - vowelCnt >= 2);
    }
}
