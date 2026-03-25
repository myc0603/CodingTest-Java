package baekjoon.clazz.class5.g4_1806;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, s;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());

        arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; ++i) arr[i] = Integer.parseInt(st.nextToken());

        int l = 0, r = 0;
        int sum = arr[0];

        int ans = n + 1;
        while (r < n) {
            if (sum >= s) {
                ans = Math.min(ans, r - l + 1);
                sum -= arr[l];
                ++l;
                if (l > r) {
                    ++r;
                    if (r >= n) break;
                    sum += arr[r];
                }
            } else {
                ++r;
                if (r >= n) break;
                sum += arr[r];
            }
        }

        System.out.println(ans > n ? 0 : ans);
    }
}
