package baekjoon.week.week3.o15684_g3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 시간 초과
public class Main {
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

        dfs(0, 0);

        System.out.println(ans > 3 ? -1 : ans);
    }

    static void dfs(int idx, int cnt) {
        if (isOk()) {
            ans = Math.min(ans, cnt);
        }
        if (cnt >= ans || idx >= h * (n - 1)) return;


        dfs(idx + 1, cnt);

        if (cnt + 1 < ans && canAdd(idx)) {
            addLadder(idx, true);
            dfs(idx + 1, cnt + 1);
            addLadder(idx, false);
        }
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
