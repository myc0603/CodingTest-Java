package baekjoon.gpt.perm_and_comb.s3_15649;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 사전순이 아니라서 해당 문제에는 적용이 안 됨
public class SwapPermutation {
    static int n;
    static int r;
    static int[] arr;

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        arr = new int[n];
        for (int i = 0; i < n; ++i) arr[i] = i + 1;

        perm(0);

        System.out.println(sb);
    }

    static void perm(int depth) {
        if (depth == r) {
            for (int i = 0; i < r; ++i) {
                sb.append(arr[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = depth; i < n; ++i) {
            swap(arr, i, depth);
            perm(depth + 1);
            swap(arr, i, depth);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }


}
