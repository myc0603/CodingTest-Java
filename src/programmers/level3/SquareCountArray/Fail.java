package programmers.level3.SquareCountArray;

import java.util.Arrays;

class Fail {
    long[] a;
    long[] psum;

    public static void main(String[] args) {
//        long[] solution = new Solution().solution(new int[]{3, 2, 3, 1, 1}, 5, 7);
//        long[] solution = new Solution().solution(new int[]{2, 2, 2}, 2, 2);
//        long[] solution = new Solution().solution(new int[]{
//                8, 8, 6, 5, 2, 9, 8, 4, 3, 10
//        }, 25, 27);

        long[] solution = new Fail().solution(new int[]{
                70195, 25471, 7389, 58187, 18454, 90532, 97667, 17148, 91636, 2810
        }, 126058, 462933);



        System.out.println("solution = " + Arrays.toString(solution));
    }

    public long[] solution(int[] arr, long l, long r) {
        l--;
        r--;

        // a = arr;
        a = new long[arr.length];
        for (int i = 0; i < arr.length; ++i) a[i] = (long) arr[i];

        psum = new long[a.length];

        psum[0] = a[0];
        int i = -1, j = -1;
        if (l < psum[0]) i = 0;
        if (r < psum[0]) j = 0;

        for (int idx = 1; idx < arr.length; ++idx) {
            psum[idx] = psum[idx - 1] + a[idx];

            if (i == -1 && l < psum[idx]) i = idx;
            if (j == -1 && r < psum[idx]) j = idx;
        }

        System.out.println("psum = " + Arrays.toString(psum));

        long k = sum(l, r, i, j);
        System.out.println("k = " + k);

        System.out.println("\n=================\n");

        long c = count(k, r - l + 1);

        return new long[]{k, c};
    }

    long sum(long l, long r, int i, int j) {
        System.out.println("Solution.sum");
        System.out.println("l = " + l + ", r = " + r + ", i = " + i + ", j = " + j);

        if (i == j) {
            long ret = a[i] * (r - l + 1);
            System.out.println("ret = " + ret);
            System.out.println();
            return a[i] * (r - l + 1);
        }

        long ret = 0;
        ret += a[i] * (psum[i] - l);
        for (int idx = i + 1; idx <= j - 1; ++idx) {
            ret += a[idx] * a[idx];
        }
        ret += a[j] * (r - psum[j - 1] + 1);

        System.out.println("ret = " + ret);
        System.out.println();

        return ret;
    }

    long count(long k, long len) {
        long cnt = 0;

        long l = 0, r = l + len - 1;
        int i = 0, j = -1;
        for (int idx = 0; idx < psum.length; ++idx) {
            if (r < psum[idx]) {
                j = idx;
                break;
            }
        }

        while (i <= j && j < a.length) {
            if (sum(l, r, i, j) == k) {
                long ll = i == 0 ? 0 : psum[i - 1];
                long rl = j == 0 ? 0 : psum[j - 1];

                cnt += Math.min(l - ll, r - rl) + Math.min(psum[i] - l, psum[j] - r);
            }

            // next l, r, i, j
            if (j == a.length - 1) break;

            if (psum[i] - l < psum[j] - r) {
                l = psum[i];
                i++;
                r = l + len - 1;
            } else if (psum[i] - l > psum[j] - r) {
                r = psum[j];
                j++;
                l = r - len + 1;
            } else {
                l = psum[i];
                i++;
                r = psum[j];
                j++;
            }
        }

        return cnt;
    }
}