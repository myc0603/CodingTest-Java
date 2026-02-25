package baekjoon.week.week1.m3986_s4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {
    public static boolean check(String s) {
        ArrayDeque<Character> stack = new ArrayDeque<>();

        char[] arr = s.toCharArray();
        for (char c : arr) {
            if (!stack.isEmpty() && stack.peek() == c) {
                stack.pop();
                continue;
            }
            stack.push(c);
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int cnt = 0;
        for (int i = 0; i < n; ++i) {
            String s = br.readLine();
            if (check(s)) ++cnt;
        }

        System.out.println(cnt);
    }
}
