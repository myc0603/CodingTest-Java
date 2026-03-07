package baekjoon.week.week3.f16637_g3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 괄호 안에 연산자 하나만 있어야 됨
// 여러 개 있어도 되는 걸로 풀었음..
public class Main {

    static int n, r, max = Integer.MIN_VALUE;
    static int[] nums, delimIdx, delimResult;
    static char[] ops;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int len = Integer.parseInt(br.readLine());

        n = (len + 1) / 2;
        nums = new int[n];
        ops = new char[n - 1];
        delimIdx = new int[n];
        for (int i = 0; i < n; ++i) delimIdx[i] = i + 1;

        String line = br.readLine();
        for (int i = 0; i < n - 1; ++i) {
            nums[i] = line.charAt(2 * i) - '0';
            ops[i] = line.charAt(2 * i + 1);
        }
        nums[n - 1] = line.charAt(len - 1) - '0';

        // r = 0: 괄호 하나도 안 치는 경우
        // r = n - 1: 모든 숫자 하나하나에 괄호가 쳐진 경우
        // 위 두개는 같은 결과라서 둘 중 하나만 포함하면 됨
        for (r = 0; r <= n - 2; ++r) {
            delimResult = new int[r + 1];
            combOfDelim(0, 0);
        }

        System.out.println(max);
    }

    static void combOfDelim(int depth, int start) {
        if (depth == r) {
            delimResult[depth] = n;
            int calculated = calc();
            max = Math.max(max, calculated);
            return;
        }

        for (int i = start; i < n - 1; ++i) {
            delimResult[depth] = delimIdx[i];
            combOfDelim(depth + 1, i + 1);
        }
    }

    static int calc() {
        int result = calcBtw(0, delimResult[0]);
        for (int i = 1; i < delimResult.length; ++i) {
            int dIdx = delimResult[i - 1];
            char op = ' ';
            op = ops[dIdx - 1];
            if (op == '+') result += calcBtw(delimResult[i - 1], delimResult[i]);
            else if (op == '-') result -= calcBtw(delimResult[i - 1], delimResult[i]);
            else if (op == '*') result *= calcBtw(delimResult[i - 1], delimResult[i]);
        }
        return result;
    }

    static int calcBtw(int d1, int d2) {
        int result = nums[d1];
        for (int i = d1; i < d2 - 1; ++i) {
            if (ops[i] == '+') result += nums[i + 1];
            else if (ops[i] == '-') result -= nums[i + 1];
            else if (ops[i] == '*') result *= nums[i + 1];
        }
        return result;
    }
}
