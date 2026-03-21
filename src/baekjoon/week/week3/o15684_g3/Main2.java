package baekjoon.week.week3.o15684_g3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2 {
    static int n, m, h, ans = 4;
    static boolean[][] ladders;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        ladders = new boolean[n][h + 1];

        for (int i = 0; i < m; ++i) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            ladders[b][a] = true;
        }

        if (isOk()) {
            System.out.println(0);
            return;
        }

        int maxIdx = h * (n - 1) - 1;
        for (int i = 0; i <= maxIdx; ++i) {
            if (!canAdd(i)) continue;
            addLadder(i, true);

            if (isOk()) {
                ans = 1;
                break;
            }

            if (ans >= 3) {
                for (int j = i + 1; j <= maxIdx; ++j) {
                    if (!canAdd(j)) continue;
                    addLadder(j, true);

                    if (isOk()) {
                        ans = 2;
                        addLadder(j, false);
                        break;
                    }

                    if (ans >= 4) {
                        for (int k = j + 1; k <= maxIdx; ++k) {
                            if (!canAdd(k)) continue;
                            addLadder(k, true);

                            if (isOk()) {
                                ans = 3;
                                addLadder(k, false);
                                break;
                            }

                            addLadder(k, false);
                        }
                    }

                    addLadder(j, false);
                }
            }
            addLadder(i, false);
        }

        System.out.println(ans > 3 ? -1 : ans);
    }

    static boolean canAdd(int idx) {
        int i = idx / h + 1;
        int j = idx % h + 1;

        if (ladders[i][j]) return false;
        if (i - 1 >= 1 && ladders[i - 1][j]) return false;
        if (i + 1 <= n - 1 && ladders[i + 1][j]) return false;
        return true;
    }

    static void addLadder(int idx, boolean isAdd) {
        int i = idx / h + 1;
        int j = idx % h + 1;

        ladders[i][j] = isAdd;
    }

    static boolean isOk() {
        for (int i = 1; i <= n; ++i) {
            int cur = i;
            for (int j = 1; j <= h; ++j) {
                if (cur <= n - 1 && ladders[cur][j]) ++cur;
                else if (cur - 1 >= 1 && ladders[cur - 1][j]) --cur;
            }
            if (cur != i) return false;
        }

        return true;
    }
}
