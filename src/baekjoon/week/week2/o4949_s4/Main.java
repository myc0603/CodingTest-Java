package baekjoon.week.week2.o4949_s4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true) {
            String line = br.readLine();
            if (line.equals(".")) break;

            if (isValid(line)) sb.append("yes").append('\n');
            else sb.append("no").append('\n');
        }
        System.out.println(sb);
    }

    static boolean isValid(String s) {
        Deque<Character> stk = new ArrayDeque<>();
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c != '(' && c != ')' && c != '[' && c != ']') continue;

            if (c == ')') {
                if (stk.isEmpty() || !stk.peek().equals('(')) return false;
                stk.pop();
            } else if (c == ']') {
                if (stk.isEmpty() || !stk.peek().equals('[')) return false;
                stk.pop();
            } else stk.push(c);
        }
        return stk.isEmpty();
    }
}
