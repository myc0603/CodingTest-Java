package baekjoon.category.greedy.s4_11047;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, k;
    static int[] coins;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        coins = new int[n];
        for (int i = 0; i < n; ++i) coins[i] = Integer.parseInt(br.readLine());

        int cnt = 0;
        int i = n - 1;
        while (k > 0) {
            cnt += k / coins[i];
            k %= coins[i];
            --i;
        }

        System.out.println(cnt);
    }
}
