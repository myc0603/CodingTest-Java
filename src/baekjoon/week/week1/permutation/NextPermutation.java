package baekjoon.week.week1.permutation;

import java.util.Arrays;

public class NextPermutation {

    static int[] arr = {1, 2, 3, 4, 5};

    static int cnt = 0;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        do {
            sb.append(++cnt).append(" - ").append(Arrays.toString(arr)).append('\n');
        } while (nextPermutation());

        System.out.println(sb);
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

    private static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}
