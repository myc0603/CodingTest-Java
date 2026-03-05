package baekjoon.week.week2.r1068_g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ParentsArray {
    static int n, k, root = -1;
    static int[] parents, aliveChildCnt;
    static boolean[] deleted;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        parents = new int[n];
        deleted = new boolean[n];
        aliveChildCnt = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; ++i) {
            parents[i] = Integer.parseInt(st.nextToken());
            if (parents[i] == -1) root = i;
        }

        k = Integer.parseInt(br.readLine());
        deleted[k] = true;

        if (k == root) {
            System.out.println(0);
            return;
        }

        // k의 자식들 모두 delete
        boolean changed = true;
        while (changed) {
            changed = false;
            for (int i = 0; i < n; ++i) {
                if (deleted[i]) continue;
                int p = parents[i];
                if (p != -1 && deleted[p]) {
                    deleted[i] = true;
                    changed = true;
                }
            }
        }

        // 살아있는 자식 수 세기
        for (int i = 0; i < n; ++i) {
            if (deleted[i]) continue;
            int p = parents[i];
            // 부모 노드가 루트가 아니고 삭제되지 않았으면 ++cnt
            if (p != -1 && !deleted[p]) ++aliveChildCnt[p];
        }

        // 리프 수 세기
        int cnt = 0;
        for (int i = 0; i < n; ++i) {
            if (!deleted[i] && aliveChildCnt[i] == 0) ++cnt;
        }
        System.out.println(cnt);
    }
}
