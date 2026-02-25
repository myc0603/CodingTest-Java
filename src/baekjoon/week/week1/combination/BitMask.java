package baekjoon.week.week1.combination;

public class BitMask {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4};
        int N = arr.length;
        int R = 2;

        // arr 의 모든 부분집합중세 bit mask 된 수가 R 개인것만 확인
        for (int mask = 0; mask < (1 << N); ++mask) {
            if (Integer.bitCount(mask) != R) continue;

            for (int i = 0; i < N; ++i) {
                // i 번째 비트 1
                if ((mask & (1 << i)) != 0) {
                    System.out.print(arr[i] + " ");
                }
            }
            System.out.println();
        }
    }
}
