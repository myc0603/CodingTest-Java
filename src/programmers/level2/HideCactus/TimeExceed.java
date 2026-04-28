package programmers.level2.HideCactus;

import java.util.Arrays;

// 시간 초과
public class TimeExceed {
    public int[] solution(int m, int n, int h, int w, int[][] drops) {
        int[][] dropTimingsAtZone = new int[m - h + 1][n - w + 1];
        for (int i = 0; i <= m - h; ++i) Arrays.fill(dropTimingsAtZone[i], m * n);

        for (int idx = 0; idx < drops.length; ++idx) {
            int i = drops[idx][0];
            int si = Math.max(0, i - h + 1);
            int ei = Math.min(i, m - h);

            int j = drops[idx][1];
            int sj = Math.max(0, j - w + 1);
            int ej = Math.min(j, n - w);

            for (int r = si; r <= ei; ++r) {
                for (int c = sj; c <= ej; ++c) {
                    dropTimingsAtZone[r][c] = Math.min(dropTimingsAtZone[r][c], idx);
                }
            }
        }

        int ansY = 0, ansX = 0;
        for (int i = 0; i <= m - h; ++i) {
            for (int j = 0; j <= n - w; ++j) {
                if (dropTimingsAtZone[i][j] > dropTimingsAtZone[ansY][ansX]) {
                    ansY = i;
                    ansX = j;
                }
            }
        }
        return new int[]{ansY, ansX};
    }
}
