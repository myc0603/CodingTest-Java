package baekjoon.category.perm_and_comb.g5_1759;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main2 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int r, n;
    static char[] chars;

    static char[] result;

    static boolean[] isVowel = new boolean[26];

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

        buildIsVowel();

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

        for (int i = start; i < n; ++i) {
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
