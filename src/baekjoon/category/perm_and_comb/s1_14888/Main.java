package baekjoon.category.perm_and_comb.s1_14888;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n;
    static int[] nums;
    static int[] ops;

    static int min = 1000000000;
    static int max = -1000000000;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        nums = new int[n];
        ops = new int[n - 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; ++i) nums[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int idx = 0;
        for (int op = 0; op < 4; ++op) {
            int opCnt = Integer.parseInt(st.nextToken());
            for (int i = 0; i < opCnt; ++i) ops[idx++] = op;
        }

        do {
            calc();
        } while (nextPermutation());

        System.out.println(max);
        System.out.println(min);
    }

    private static void calc() {
        int result = nums[0];
        for (int i = 1; i < n; ++i) {
            switch (ops[i-1]) {
                case 0:
                    result += nums[i];
                    break;
                case 1:
                    result -= nums[i];
                    break;
                case 2:
                    result *= nums[i];
                    break;
                case 3:
                    result /= nums[i];
                    break;
            }
        }
        min = Math.min(min, result);
        max = Math.max(max, result);
    }

    private static boolean nextPermutation() {
        int i = n - 2;
        while (i > 0 && ops[i - 1] >= ops[i]) --i;
        if (i == 0) return false;

        int j = n - 2;
        while (ops[i - 1] >= ops[j]) --j;
        swap(ops, i - 1, j);

        int k = n - 2;
        while (i < k) swap(ops, i++, k--);
        return true;
    }

    private static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

}
