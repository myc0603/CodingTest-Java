package baekjoon.week.week2.m1436_s5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int cnt = 0;
        for (int i = 666; ; ++i) {
            if (String.valueOf(i).contains("666")) {
                ++cnt;
                if (n == cnt) {
                    System.out.println(i);
                    return;
                }
            }
        }
    }
}
