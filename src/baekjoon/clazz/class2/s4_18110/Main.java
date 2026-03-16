package baekjoon.clazz.class2.s4_18110;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int n;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        arr = new int[n];

        for (int i = 0; i < n; ++i) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        int per15 = (int) Math.round(n * 0.15d);
        int tot = 0;
        for (int i = per15; i < n - per15; ++i) {
            tot += arr[i];
        }

        System.out.println(Math.round((double) tot / (n - 2 * per15)));
    }
}
