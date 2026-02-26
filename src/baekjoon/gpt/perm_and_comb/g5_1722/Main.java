package baekjoon.gpt.perm_and_comb.g5_1722;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int n, q;
    static long k;
    static int[] arr, tmp;

    static long[] fact = new long[22];

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        q = Integer.parseInt(st.nextToken());

        arr = new int[n];
        for (int i = 0; i < n; ++i) arr[i] = i + 1;
        tmp = new int[n];

        fact[0] = 1;

        if (q == 1) {
            k = Long.parseLong(st.nextToken());

            // k 번째 순열 구하기
            int[] result = getArr(k);
            for (int i = 0; i < n; ++i) sb.append(result[i]).append(' ');
        } else {
            for (int i = 0; i < n; ++i) tmp[i] = Integer.parseInt(st.nextToken());

            // tmp 가 몇번째 순열인지 구하기
            sb.append(getOrder());
        }

        System.out.println(sb);
    }

    private static int[] getArr(long k) {
        int[] result = new int[n];
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < n; ++i) {
            int x = 1;
            while (set.contains(x)) ++x;
            while (k > factorial(n - 1 - i)) {
                do ++x;
                while (set.contains(x));
                k -= factorial(n - 1 - i);
            }
            set.add(x);
            result[i] = x;
        }
        return result;
    }

    private static long getOrder() {
        long cnt = 1;
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < n; ++i) {
            for (int j = 1; j < tmp[i]; ++j) {
                if (set.contains(j)) continue;
                cnt += factorial(n - 1 - i);
            }
            set.add(tmp[i]);
        }
        return cnt;
    }

    private static long factorial(int N) {
        if (fact[N] != 0) return fact[N];
        long result = 1;
        for (int i = 1; i <= N; ++i) {
            result *= i;
            fact[i] = result;
        }
        return result;
    }
}
