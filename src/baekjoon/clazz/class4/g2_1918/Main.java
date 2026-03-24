package baekjoon.clazz.class4.g2_1918;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {
    static char[] expr;

    static class OpWithDepth {
        char op;
        int depth;

        OpWithDepth(char op, int depth) {
            this.op = op;
            this.depth = depth;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        expr = br.readLine().toCharArray();

        StringBuilder sb = new StringBuilder();

        ArrayDeque<OpWithDepth> stk = new ArrayDeque<>();
        int depth = 0;
        for (char c : expr) {
            if (c == ')') {
                depth--;
                continue;
            }
            if (c == '(') {
                depth++;
                continue;
            }
            if (isNotOperator(c)) {
                sb.append(c);
                continue;
            }

            OpWithDepth cur = new OpWithDepth(c, depth);
            while (shouldPop(cur, stk)) sb.append(stk.pop().op);
            stk.push(cur);
        }

        while (!stk.isEmpty()) sb.append(stk.pop().op);

        System.out.println(sb);
    }

    static boolean isNotOperator(char c) {
        return c != '+' && c != '-' && c != '*' && c != '/';
    }

    static boolean shouldPop(OpWithDepth cur, ArrayDeque<OpWithDepth> stk) {
        if (stk.isEmpty()) return false;

        OpWithDepth higher = stk.peek();
        if (cur.depth == higher.depth) {
            return (higher.op == '*' || higher.op == '/') || (cur.op == '+' || cur.op == '-');
        }
        return cur.depth < higher.depth;
    }
}
