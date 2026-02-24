package baekjoon.week.week1.e1159_b2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] cnt = new int[30];
        for (int i = 0; i < n; ++i) {
            String name = br.readLine();
            char c = name.charAt(0);
            ++cnt[c - 'a'];
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= 26; ++i) {
            if (cnt[i] >= 5) {
                sb.append((char) (i + 'a'));
            }
        }
        if (sb.isEmpty()) System.out.println("PREDAJA");
        System.out.println(sb);
    }
}
