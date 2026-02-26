package baekjoon.week.week1.permutation;

public class SwapPermutation {

    static int[] arr = {1, 2, 3, 4, 5};
    static int n = arr.length;
    static int r = 5;

    static int cnt = 0;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        swapPermutation(0);
        System.out.println(sb);
    }

    private static void swapPermutation(int depth) {
        if (depth == r) {
            sb.append(++cnt).append(" - ");
            for (int i = 0; i < r; ++i) {
                sb.append(arr[i]).append(' ');
            }
            sb.append('\n');
            return;
        }

        for (int i = depth; i < n; ++i) {
            swap(arr, depth, i);
            swapPermutation(depth + 1);
            swap(arr, depth, i);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

}
