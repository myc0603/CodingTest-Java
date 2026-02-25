package baekjoon.week.week1.permutation;

import java.util.Arrays;

public class NextPermutation {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4};

        Arrays.sort(arr);

        do {
            System.out.println(Arrays.toString(arr));
        } while (nextPermutation(arr));
    }

    static boolean nextPermutation(int[] arr) {
        int i = arr.length - 1;

        while (i > 0 && arr[i - 1] >= arr[i]) --i;

        if (i == 0) return false;

        int j = arr.length - 1;

        while (arr[i - 1] >= arr[j]) --j;

        swap(arr, i - 1, j);

        int k = arr.length - 1;
        while (i < k) {
            swap(arr, i, k);
            ++i;
            --k;
        }

        return true;
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
