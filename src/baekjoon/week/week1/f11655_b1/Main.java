package baekjoon.week.week1.f11655_b1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        char[] charArray = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char c : charArray) {
            if ('a' <= c && c <= 'z') {
                sb.append((char) (((c - 'a' + 13) % 26) + 'a'));
            } else if ('A' <= c && c <= 'Z') {
                sb.append((char) (((c - 'A' + 13) % 26) + 'A'));
            } else {
                sb.append(c);
            }
        }
        System.out.println(sb);
    }
}
