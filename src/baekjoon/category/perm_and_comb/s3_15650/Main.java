package baekjoon.category.perm_and_comb.s3_15650;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int n, r;
    static int[] arr;
    static int[] result;

    // 오름차순이라는 순서가 정해져 있음 -> 조합
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        arr = new int[n];
        for (int i = 0; i < n; ++i) arr[i] = i + 1;
        result = new int[r];

        comb(0, 0);

        System.out.println(sb);
    }

    private static void comb(int depth, int start) {
        if (depth == r) {
            for (int i = 0; i < r; ++i) {
                sb.append(result[i]).append(' ');
            }
            sb.append('\n');
            return;
        }

        for (int i = start; i < n; ++i) {
            result[depth] = arr[i];
            comb(depth + 1, i + 1);
        }
    }
}
