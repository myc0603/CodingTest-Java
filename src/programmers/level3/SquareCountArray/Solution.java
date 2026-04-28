package programmers.level3.SquareCountArray;

import java.util.Arrays;

public class Solution {
    int[] a;
    long[] psum;

    public static void main(String[] args) {
        test(new int[]{1, 2, 3, 4, 5}, 5, 7);
        test(new int[]{3, 2, 3, 1, 1}, 5, 7);
        test(new int[]{2, 2, 2}, 2, 2);
        test(new int[]{8, 8, 6, 5, 2, 9, 8, 4, 3, 10}, 25, 27);
        test(new int[]{70195, 25471, 7389, 58187, 18454, 90532, 97667, 17148, 91636, 2810}, 126058, 462933);
        test(new int[]{16952, 70276, 16771, 37992, 87549, 54906, 36718, 20478, 57088, 27916, 51509, 83422, 51707, 18807, 80859, 2673, 37734, 93380}, 149845, 228204);
        test(new int[]{49134, 86806, 94548, 88849, 95022, 28334, 16637, 79487, 23773, 7314, 47370, 50269, 36573, 9415, 44674, 28096}, 61242, 88535);
    }

    static void test(int[] arr, long l, long r) {
        long[] result = new Solution().solution(arr, l, r);
        System.out.println(Arrays.toString(result));
    }

    public long[] solution(int[] arr, long l, long r) {
        a = arr;

        l--;
        r--;

        int i = 0, j = 0;
        psum = new long[arr.length + 1];
        for (int m = 1; m <= arr.length; ++m) {
            psum[m] = psum[m - 1] + arr[m - 1];
            if (i == 0 && l < psum[m]) i = m - 1;
            if (j == 0 && r < psum[m]) j = m - 1;
        }

        long k = getK(l, r, i, j);
        long c = getC(r - l + 1, k);

        return new long[]{k, c};
    }

    long getK(long l, long r, int i, int j) {
        if (i == j) return a[i] * (r - l + 1);

        long ret = 0;

        ret += (long) a[i] * (psum[i + 1] - l);

        for (int m = i + 1; m <= j - 1; ++m) {
            ret += (long) a[m] * a[m];
        }

        ret += (long) a[j] * (r - psum[j] + 1);

        return ret;
    }

    long getC(long len, long k) {
        long cnt = 0;

        long l = 0;
        long r = l + len - 1;

        int i = 0;
        int j = 0;
        for (int m = 0; m <= a.length; ++m) {
            if (r < psum[m]) {
                j = m - 1;
                break;
            }
        }

        while (i < a.length && j < a.length) {
            long sum = getK(l, r, i, j);

            if (sum == k) {
                if (a[i] == a[j]) cnt += Math.min(l - psum[i], r - psum[j]) + Math.min(psum[i + 1] - l, psum[j + 1] - r);
                else cnt++;
            } else {
                long diffWithK = Math.abs(sum - k);
                long diffBtwIJ = Math.abs(a[i] - a[j]);

                if (diffBtwIJ != 0 && diffWithK % diffBtwIJ == 0) {
                    // has to go left
                    if ((sum < k && a[i] > a[j]) || (sum > k && a[i] < a[j])) {
                        if (Math.min(l - psum[i], r - psum[j]) >= diffWithK / diffBtwIJ) {
                            cnt++;
                        }
                    }

                    // has to go right
                    if ((sum < k && a[i] < a[j]) || (sum > k && a[i] > a[j])) {
                        if (Math.min(psum[i + 1] - l - 1, psum[j + 1] - r - 1) >= diffWithK / diffBtwIJ) {
                            cnt++;
                        }
                    }
                }
            }

            if (psum[i + 1] - l < psum[j + 1] - r) {
                l = psum[i + 1];
                i++;
                r = l + len - 1;
            } else {
                r = psum[j + 1];
                j++;
                l = r - len + 1;

                if (l == psum[i + 1]) i++;
            }
        }

        return cnt;
    }
}
