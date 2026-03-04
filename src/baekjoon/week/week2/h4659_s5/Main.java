package baekjoon.week.week2.h4659_s5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static boolean[] isVowel = new boolean[26];
    static {
        isVowel['a' - 'a'] = true;
        isVowel['e' - 'a'] = true;
        isVowel['i' - 'a'] = true;
        isVowel['o' - 'a'] = true;
        isVowel['u' - 'a'] = true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String line;
        while ((line = br.readLine()) != null) {
            if (line.equals("end")) break;
            sb.append('<').append(line).append("> is");
            if (!isAcceptable(line)) sb.append(" not");
            sb.append(" acceptable.\n");
        }
        System.out.println(sb);
    }

    static boolean isAcceptable(String s) {
        char prev = ' ';
        int vowelCnt = 0;
        int v = 0;
        int c = 0;
        for (int i = 0; i < s.length(); ++i) {
            char cur = s.charAt(i);
            if (prev == cur && cur != 'e' && cur != 'o') return false;
            if (isVowel[cur - 'a']) { // cur가 모음
                ++vowelCnt;
                c = 0;
                ++v;
            } else {
                v = 0;
                ++c;
            }
            if (v == 3 || c == 3) return false;
            prev = cur;
        }
        return vowelCnt >= 1;
    }
}
