package baekjoon.gpt.perm_and_comb.s1_14888;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Standard {
    static int n;
    static int[] nums;
    static int[] cnt = new int[4];
    static int min = Integer.MAX_VALUE;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        nums = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; ++i) nums[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; ++i) cnt[i] = Integer.parseInt(st.nextToken());

        dfs(1, nums[0]);

        System.out.println(max);
        System.out.println(min);
    }

    private static void dfs(int idx, int cur) {
        if (idx == n) {
            min = Math.min(min, cur);
            max = Math.max(max, cur);
            return;
        }

        for (int op = 0; op < 4; ++op) {
            if (cnt[op] == 0) continue;
            --cnt[op];
            dfs(idx + 1, calc(cur, op, nums[idx]));
            ++cnt[op];
        }
    }

    private static int calc(int x, int op, int y) {
        return switch (op) {
            case 0 -> x + y;
            case 1 -> x - y;
            case 2 -> x * y;
            default -> x / y;
        };
    }
}
