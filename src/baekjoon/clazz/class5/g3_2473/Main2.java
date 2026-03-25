package baekjoon.clazz.class5.g3_2473;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// 기존 풀이와 동일한데 아주 살짝만 수정
public class Main2 {
    static int n;
    static long[] arr;

    static long[] ans = new long[3];
    static long minSum = Long.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new long[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; ++i) arr[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(arr);

        if (0 <= arr[0]) {
            System.out.println(String.valueOf(arr[0]) + ' ' + arr[1] + ' ' + arr[2]);
            return;
        }

        if (arr[n - 1] <= 0) {
            System.out.println(String.valueOf(arr[n - 3]) + ' ' + arr[n - 2] + ' ' + arr[n - 1]);
            return;
        }

        // 0보다 작거나 같은 수 중 가장 큰 수의 인덱스
        int zeroIdx = bs(0, 0, n - 1);
        if (arr[zeroIdx] < 0) ++zeroIdx;

        if (zeroIdx + 2 < n) check(zeroIdx, zeroIdx + 1, zeroIdx + 2);
        if (zeroIdx - 3 >= 0) check(zeroIdx - 3, zeroIdx - 2, zeroIdx - 1);

        // neg 2 : pos 1
        if (zeroIdx >= 2) {
            for (int i = 0; i < zeroIdx; ++i) {
                for (int j = i + 1; j < zeroIdx; ++j) {
                    int k = bs(-(arr[i] + arr[j]), zeroIdx, n - 1);

                    check(i, j, k);
                    check(i, j, k + 1);
                }
            }
        }

        // neg 1 : pos 2
        if (zeroIdx <= n - 2) {
            for (int i = zeroIdx; i < n; ++i) {
                for (int j = i + 1; j < n; ++j) {
                    int k = bs(-(arr[i] + arr[j]), 0, zeroIdx - 1);

                    check(k, i, j);
                    check(k + 1, i, j);
                }
            }
        }

        System.out.println(String.valueOf(ans[0]) + ' ' + ans[1] + ' ' + ans[2]);
    }

    private static void check(int i, int j, int k) {
        long sum = Math.abs(arr[i] + arr[j] + arr[k]);
        if (sum < minSum) {
            minSum = sum;
            ans[0] = arr[i];
            ans[1] = arr[j];
            ans[2] = arr[k];
        }
    }

    static int bs(long target, int l, int r) {
        while (l + 1 < r) {
            int mid = (l + r) / 2;
            if (target < arr[mid]) r = mid;
            else l = mid;
        }

        return l;
    }
}
