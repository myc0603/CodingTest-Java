package baekjoon.clazz.class5.g3_9252;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main2 {
    static String s1, s2;
    static int[][] dp;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        s1 = br.readLine();
        s2 = br.readLine();

        dp = new int[s1.length() + 1][s2.length() + 1];

        for (int i = 1; i <= s1.length(); ++i) {
            for (int j = 1; j <= s2.length(); ++j) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }

        // lcs 길이까지 sb.append 해서 뒤집으면 안됨 (12 -> 21)
        System.out.println(dp[s1.length()][s2.length()]);
        if (dp[s1.length()][s2.length()] == 0) return;

        int i = s1.length(), j = s2.length();
        // while 문 조건은 다 되는 듯
        while (i > 0 && j > 0) {
//            while (dp[i][j] > 0) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                sb.append(s1.charAt(i - 1));
                i--;
                j--;
            } else {
                if (dp[i][j - 1] >= dp[i - 1][j]) j--;
                else i--;
            }
        }

        System.out.println(sb.reverse());
    }
}
