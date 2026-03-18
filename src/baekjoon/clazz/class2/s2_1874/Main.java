package baekjoon.clazz.class2.s2_1874;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        ArrayDeque<Integer> stk = new ArrayDeque<>();

        StringBuilder sb = new StringBuilder();
        int toPush = 1;
        for (int i = 0; i < n; ++i) {
            int cur = Integer.parseInt(br.readLine());
            while (cur >= toPush) {
                stk.push(toPush++);
                sb.append('+').append('\n');
            }
            if (stk.peek() != cur) {
                System.out.println("NO");
                return;
            }

            stk.pop();
            sb.append('-').append('\n');
        }

        System.out.println(sb);
    }
}
