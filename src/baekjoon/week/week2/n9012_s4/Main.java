package baekjoon.week.week2.n9012_s4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            String s = br.readLine();
            if (isValid(s)) sb.append("YES").append('\n');
            else sb.append("NO").append('\n');
        }
        System.out.println(sb);
    }

    static boolean isValid(String s) {
        ArrayDeque<Character> stk = new ArrayDeque<>();
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c == ')') {
                if (!stk.isEmpty()) stk.pop();
                else return false;
            } else {
                stk.push(c);
            }
        }

        return stk.isEmpty();
    }
}
