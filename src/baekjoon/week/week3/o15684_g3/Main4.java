package baekjoon.week.week3.o15684_g3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// fail
// while pruning 과 탐색 순서가 맞지 않음
public class Main4 {
    static int n, m, h, maxIdx, ans = 4;
    static boolean[][] ladders;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        ladders = new boolean[n + 1][h + 2];
        maxIdx = (n - 1) * h - 1;

        for (int i = 0; i < m; ++i) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            ladders[b][a] = true;
        }

        dfs(0, 0);

        System.out.println(ans > 3 ? -1 : ans);
    }

    static void dfs(int start, int cnt) {
        if (ans <= cnt) return;
        if (isOk()) {
            ans = cnt;
            return;
        }

        if (ans <= cnt + 1) return;
        for (int idx = start; idx <= maxIdx; ++idx) {
            if (!canAdd(idx)) continue;
            addLadder(idx, true);
            dfs(idx + 1, cnt + 1);
            addLadder(idx, false);

            // pruning
            int i = idx / h + 1;
            int j = idx % h + 1;
            System.out.println("pruning");
            System.out.println("i: " + i + ", j: " + j);

            while (j <= h && !ladders[i - 1][j] && !ladders[i + 1][j]) ++j;
            System.out.println("after pruning: j: " + j);
            System.out.println();

            idx = (i - 1) * h + (--j - 1);
        }
    }

    static boolean canAdd(int idx) {
        int i = idx / h + 1;
        int j = idx % h + 1;

        return !ladders[i][j] && !ladders[i - 1][j] && !ladders[i + 1][j];
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
                if (ladders[cur - 1][j]) --cur;
                else if (ladders[cur][j]) ++cur;
            }

            if (cur != i) return false;
        }

        return true;
    }
}

/*
3 3 6
1 2
2 1
3 2

expected: 3
but: -1
 */
