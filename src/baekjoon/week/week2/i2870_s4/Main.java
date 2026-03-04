package baekjoon.week.week2.i2870_s4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    static int n;
    static ArrayList<String> arr = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; ++i) {
            String line = br.readLine();
            getNumberFrom(line);
        }
        arr.sort((s1, s2) -> {
            if (s1.length() == s2.length()) {
                for (int i = 0; i < s1.length(); ++i) {
                    if (s1.charAt(i) == s2.charAt(i)) continue;
                    return s1.charAt(i) - s2.charAt(i);
                }
            }
            return s1.length() - s2.length();
        });

        StringBuilder sb = new StringBuilder();
        for (String x : arr) sb.append(x).append('\n');
        System.out.println(sb);
    }

    static void getNumberFrom(String s) {
        StringBuilder sb = new StringBuilder();
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if ('0' <= c && c <= '9') sb.append(c);
            else {
                if (!sb.isEmpty()) {
                    while (sb.charAt(0) == '0' && sb.length() > 1) sb.deleteCharAt(0);
                    arr.add(sb.toString());
                }
                sb.setLength(0);
            }
        }
        if (!sb.isEmpty()) {
            while (sb.charAt(0) == '0' && sb.length() > 1) sb.deleteCharAt(0);
            arr.add(sb.toString());
        }
    }
}
