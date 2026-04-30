package programmers.level2.HideCactus;

import java.util.Arrays;

public class TimeExceed2 {
    public int[] solution(int m, int n, int h, int w, int[][] drops) {
        int[][] timings = new int[m][n];
        for (int i = 0; i < m; ++i) Arrays.fill(timings[i], m * n);

        for (int t = 0; t < drops.length; ++t) {
            int y = drops[t][0], x = drops[t][1];
            timings[y][x] = t + 1;
        }

        int y = -1, x = -1;
        int curDrop = -1;
        for (int i = 0; i <= m - h; ++i) {
            for (int j = 0; j <= n - w; ++j) {
                int firstDrop = m * n;
                for (int ii = i; ii < i + h; ++ii) {
                    for (int jj = j; jj < j + w; ++jj) {
                        if (timings[ii][jj] < firstDrop) firstDrop = timings[ii][jj];
                    }
                }

                if (firstDrop > curDrop) {
                    curDrop = firstDrop;
                    y = i;
                    x = j;
                }
            }
        }

        return new int[]{y, x};
    }
}
