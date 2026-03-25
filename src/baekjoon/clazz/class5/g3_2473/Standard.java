package baekjoon.clazz.class5.g3_2473;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Standard {
    static int n;
    static long[] arr;

    static long minAbsSum = Long.MAX_VALUE;
    static long[] ans = new long[3];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new long[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; ++i) arr[i] = Long.parseLong(st.nextToken());
        Arrays.sort(arr);

        for (int i = 0; i < n - 2; ++i) {
            int l = i + 1, r = n - 1;
            while (l < r) {
                long sum = arr[i] + arr[l] + arr[r];
                long abs = Math.abs(sum);

                // 얘를 먼저 해야 l, r 바뀌전에 ans, minAbsSum 갱신
                // 먼저 하지 않으면 첫 i, l, r 값에 대한 확인이 안 됨
                if (abs < minAbsSum) {
                    minAbsSum = abs;
                    ans[0] = arr[i];
                    ans[1] = arr[l];
                    ans[2] = arr[r];
                }

                if (sum > 0) --r;
                else if (sum < 0) ++l;
                else {
                    System.out.println(ans[0] + " " + ans[1] + " " + ans[2]);
                    return;
                }
            }
        }

        System.out.println(ans[0] + " " + ans[1] + " " + ans[2]);
    }

}
