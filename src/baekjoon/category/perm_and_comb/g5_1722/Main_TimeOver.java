package baekjoon.category.perm_and_comb.g5_1722;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_TimeOver {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int n, q, k;
    static int[] arr;
    static int[] temp;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        for (int i = 0; i < n; ++i) arr[i] = i + 1;
        temp = new int[n];

        st = new StringTokenizer(br.readLine());
        q = Integer.parseInt(st.nextToken());
        int cnt = 0;
        if(q == 1) {
            k = Integer.parseInt(st.nextToken());
            // k번째 순열을 구하기
            do {
                ++cnt;
                if (cnt == k) {
                    for (int i = 0; i < n; ++i) sb.append(arr[i]).append(' ');
                    break;
                }
            } while (nextPermutation());
        } else {
            for (int i = 0; i < n; ++i) {
                temp[i] = Integer.parseInt(st.nextToken());
            }
            // 순열 temp가 몇번째 순열인지
            do {
                ++cnt;
                if (isSame()) {
                    sb.append(cnt);
                    break;
                }
            } while (nextPermutation());
        }

        System.out.println(sb);
    }

    private static boolean isSame() {
        for (int i = 0; i < n; ++i) {
            if (arr[i] != temp[i]) return false;
        }
        return true;
    }

    private static boolean nextPermutation() {
        int i = arr.length - 1;
        while (i > 0 && arr[i - 1] >= arr[i]) --i;
        if (i == 0) return false;

        int j = arr.length - 1;
        while (arr[i - 1] >= arr[j]) --j;
        swap(arr, i - 1, j);

        int k = arr.length - 1;
        while (i < k) swap(arr, i++, k--);
        return true;
    }

    private static void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}
