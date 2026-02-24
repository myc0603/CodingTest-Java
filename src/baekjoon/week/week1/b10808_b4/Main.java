package baekjoon.week.week1.b10808_b4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();

        int[] cnt = new int[26];

        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            ++cnt[c - 'a'];
        }

        StringBuilder sb = new StringBuilder();
        for (int v : cnt) {
            sb.append(v).append(' ');
        }
        System.out.println(sb);

    }
}
