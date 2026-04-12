package baekjoon.category.perm_and_comb.g5_1722;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Standard {
    static StringBuilder sb = new StringBuilder();

    static int n, q;
    static ArrayList<Integer> arr;
    static int[] result;
    static long k = 1;
    static long[] fact = new long[21];

    public static void main(String[] args) throws IOException {
        buildFactArr();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new ArrayList<>(n);
        for (int i = 0; i < n; ++i) arr.add(i + 1);
        result = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        q = Integer.parseInt(st.nextToken());

        if (q == 1) {
//            k = Integer.parseInt(st.nextToken());
            k = Long.parseLong(st.nextToken());

            // k번째 순열 구하기
            sol1();
            for (int i = 0; i < n; ++i) sb.append(result[i]).append(' ');
        } else {
            for (int i = 0; i < n; ++i) result[i] = Integer.parseInt(st.nextToken());

            // result가 몇번째 배열인지 구하기
            sol2();
            sb.append(k);
        }

        System.out.println(sb);
    }

    private static void buildFactArr() {
        fact[0] = 1;
//        for (int i = 1; i < 20; ++i) fact[i] = fact[i - 1] * i;
        for (int i = 1; i <= 20; ++i) fact[i] = fact[i - 1] * i;
    }

    // k번째 순열 구하기
    private static void sol1() {
        --k;
        for (int i = 0; i < n; ++i) {
            int idx = (int) (k / fact[n - 1 - i]);
            result[i] = arr.get(idx);
            arr.remove(idx);
            k %= fact[n - 1 - i];
        }
    }

    // result가 몇번째 배열인지 구하기
    private static void sol2() {
        for (int i = 0; i < n; ++i) {
            int idx = arr.indexOf(result[i]);
            k += idx * fact[n - 1 - i];
            arr.remove(idx);
        }
    }
}
