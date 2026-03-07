package baekjoon.week.week3.f16637_g3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main2 {
    static int n, max = Integer.MIN_VALUE;
    static int[] nums;
    static char[] ops;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int len = Integer.parseInt(br.readLine());

        n = (len + 1) / 2;
        nums = new int[n];
        ops = new char[n - 1];

        String line = br.readLine();
        for (int i = 0; i < n; ++i) {
            nums[i] = line.charAt(2 * i) - '0';
            if (i < n - 1) ops[i] = line.charAt(2 * i + 1);
        }

        dfs(1, nums[0]);

        System.out.println(max);
    }

    static void dfs(int idx, int cur) {
        if (idx >= n) {
            max = Math.max(max, cur);
            return;
        }

        dfs(idx + 1, calc(cur, ops[idx - 1], nums[idx]));

        if (idx + 1 < n) {
            int bracketValue = calc(nums[idx], ops[idx], nums[idx + 1]);
            dfs(idx + 2, calc(cur, ops[idx - 1], bracketValue));
        }
    }

    static int calc(int x, char op, int y) {
        if (op == '+') return x + y;
        if (op == '-') return x - y;
        return x * y;
    }
}
