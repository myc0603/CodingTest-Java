package baekjoon.gpt.perm_and_comb.s3_15649;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class NextPermutation {
    static int n;
    static int r;
    static int[] arr;

    static StringBuilder sb = new StringBuilder();

    // 중복 때문에 안됨
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        arr = new int[n];
        for (int i = 0; i < n; ++i) arr[i] = i + 1;

        do {
            for (int i = 0; i < r; ++i) sb.append(arr[i]).append(' ');
            sb.append('\n');
        } while (nextPermutation(arr));

        System.out.println(sb);
    }

    private static boolean nextPermutation(int[] a) {
        int i = a.length - 1;
        while (i > 0 && a[i - 1] >= a[i]) --i;
        if (i == 0) return false;

        int j = a.length - 1;
        while (a[i - 1] >= a[j]) --j;

        swap(a, i - 1, j);

        int k = a.length - 1;
        while (i < k) swap(a, i++, k--);

        return true;
    }

    private static void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }


}
