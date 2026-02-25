package baekjoon.week.week1.permutation;

public class SwapPermutation {

    static int[] arr = {1, 2, 3, 4, 5};
    static int N = arr.length;
    static int R = 3;
    static int cnt = 0;

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        swapPermutation(0);
        System.out.println(sb);
    }

    private static void swapPermutation(int depth) {
        if (depth == R) {
            sb.append(++cnt + " - ");
            for (int i = 0; i < R; ++i) sb.append(arr[i]).append(' ');
            sb.append('\n');
            return;
        }

        for (int i = depth; i < N; ++i) {
            swap(arr, depth, i);
            swapPermutation(depth + 1);
            swap(arr, depth, i);
        }
    }

    private static void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}
