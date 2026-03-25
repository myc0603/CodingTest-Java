package baekjoon.clazz.class5.g3_2473;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 굳이 음수, 양수 개수 나눠서 할 필요 x (https://justicehui.github.io/koi/2019/02/27/BOJ2473)
public class Main3 {
    static int n;
    static long[] arr;

    static long[] ans;
    static long minSum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new long[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; ++i) arr[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(arr);

        ans = new long[]{arr[0], arr[1], arr[2]};
        minSum = Math.abs(arr[0] + arr[1] + arr[2]);

        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                int k = lowerBound(-(arr[i] + arr[j]), j + 1, n);
                check(i, j, k - 1);
                check(i, j, k);
            }
        }

        System.out.println(ans[0] + " " + ans[1] + " " + ans[2]);
    }

    private static void check(int i, int j, int k) {
        if (j < k && k < n){
            long sum = Math.abs(arr[i] + arr[j] + arr[k]);
            if (sum < minSum) {
                minSum = sum;
                ans[0] = arr[i];
                ans[1] = arr[j];
                ans[2] = arr[k];
            }
        }
    }

    static int lowerBound(long target, int l, int r) {
        while (l < r) {
            int mid = (l + r) / 2;
            if (arr[mid] < target) l = mid + 1;
            else r = mid;
        }
        return l;
    }
}
