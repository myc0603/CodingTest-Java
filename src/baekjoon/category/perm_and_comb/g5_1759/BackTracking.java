package baekjoon.category.perm_and_comb.g5_1759;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BackTracking {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int r, n;
    static char[] chars;
    static char[] result;
    static boolean[] isVowel = new boolean[26];

    static int initVowelCnt, initConsCnt;

    public static void main(String[] args) throws IOException {
        buildIsVowel();

        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        chars = new char[n];
        result = new char[r];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; ++i) {
            chars[i] = st.nextToken().charAt(0);
            if (isVowel[chars[i] - 'a']) ++initVowelCnt;
            else ++initConsCnt;
        }

        // back tracking(?)
        if (initVowelCnt < 1 || initConsCnt < 2) return;

        Arrays.sort(chars);

        comb(0, 0, 0, 0);

        System.out.println(sb);
    }

    private static void comb(int depth, int start, int vowelCnt, int consCnt) {
        if (depth == r) {
            if (vowelCnt >= 1 && consCnt >= 2) {
                sb.append(result).append('\n');
            }
            return;
        }

        // back tracking
        int remain = r - depth;
        if (remain + vowelCnt < 1) return;
        if (remain + consCnt < 2) return;

        for (int i = start; i < n; ++i) {
            // back tracking
            if (n - i < remain) break;

            result[depth] = chars[i];
            int nv = isVowel[chars[i] - 'a'] ? vowelCnt + 1 : vowelCnt;
            int nc = isVowel[chars[i] - 'a'] ? consCnt : consCnt + 1;
            comb(depth + 1, i + 1, nv, nc);
        }
    }

    private static void buildIsVowel() {
        isVowel['a' - 'a'] = true;
        isVowel['e' - 'a'] = true;
        isVowel['i' - 'a'] = true;
        isVowel['o' - 'a'] = true;
        isVowel['u' - 'a'] = true;
    }
}
