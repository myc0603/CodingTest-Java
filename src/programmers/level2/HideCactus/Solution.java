package programmers.level2.HideCactus;

import java.util.ArrayDeque;
import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        test(4, 5, 2, 2, new int[][]{
                {0, 0}, {3, 1}, {1, 3}, {2, 4},
                {1, 1}, {2, 2}, {2, 3}, {0, 4}
        });

        test(3, 3, 1, 1, new int[][]{
                {0, 0}, {0, 1}, {0, 2}, {1, 0}
        });

        test(4, 6, 3, 4, new int[][]{
                {1, 2}
        });

        test(4, 6, 1, 2, new int[][]{
                {0, 1}, {0, 3}, {0, 5},
                {1, 1}, {1, 3}, {1, 5},
                {2, 1}, {2, 3}, {2, 5},
                {3, 1}, {3, 3}, {3, 5}
        });

        test(2, 2, 2, 2, new int[][]{
                {0, 0}, {0, 1}, {1, 1}, {1, 0}
        });

        test(4, 4, 3, 1, new int[][]{
                {2, 0}, {1, 3}, {3, 2}, {0, 1}
        });
    }

    static void test(int m, int n, int h, int w, int[][] drops) {
        int[] result = new Solution().solution(m, n, h, w, drops);
        System.out.println(Arrays.toString(result));
    }

    public int[] solution(int m, int n, int h, int w, int[][] drops) {
        int INF = drops.length;

        int[][] time = new int[m][n];
        for (int i = 0; i < m; ++i) Arrays.fill(time[i], INF);

        for (int t = 0; t < drops.length; ++t) {
            int y = drops[t][0], x = drops[t][1];
            time[y][x] = t;
        }

        int[][] rowMin = new int[m][n - w + 1];

        for (int y = 0; y < m; ++y) {
            ArrayDeque<Integer> deque = new ArrayDeque<>();

            for (int x = 0; x < n; ++x) {
                // 현재 값보다 작거나 같은 값들은 모두 pollLast
                while (!deque.isEmpty() && time[y][deque.peekLast()] >= time[y][x]) {
                    deque.pollLast();
                }

                // x 값을 최솟값 후보로 추가
                deque.addLast(x);

                // 맨 앞의 값이 범위 밖이면 poll
                if (deque.peekFirst() < x - w + 1) {
                    deque.pollFirst();
                }

                // 구간이 w 이상이면 rowMin 갱신
                if (x >= w - 1) {
                    int sx = x - w + 1;
                    rowMin[y][sx] = time[y][deque.peekFirst()];
                }
            }
        }

        int bestY = -1, bestX = -1;
//        int bestY = 0, bestX = 0;
        int bestTime = -1;

        for (int x = 0; x <= n - w; ++x) {
            ArrayDeque<Integer> deque = new ArrayDeque<>();

            for (int y = 0; y < m; ++y) {
                while (!deque.isEmpty() && rowMin[deque.peekLast()][x] >= rowMin[y][x]) {
                    deque.pollLast();
                }

                deque.addLast(y);

                if (deque.peekFirst() < y - h + 1) {
                    deque.pollFirst();
                }

                if (y >= h - 1) {
                    int sy = y - h + 1;

                    int wetTime = rowMin[deque.peekFirst()][x];
                    if ((wetTime > bestTime) || (wetTime == bestTime && sy < bestY)) {
                        bestTime = wetTime;
                        bestY = sy;
                        bestX = x;
                    }
                }
            }
        }


        return new int[]{bestY, bestX};
    }
}
