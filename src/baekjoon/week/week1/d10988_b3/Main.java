package baekjoon.week.week1.d10988_b3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();

        // len = 10, 11 => 0 ~ 4
        for (int i = 0; i < s.length() / 2; ++i) {
            if (s.charAt(i) != s.charAt(s.length() - i - 1)) {
                System.out.println(0);
                return;
            }
        }
        System.out.println(1);
    }
}
