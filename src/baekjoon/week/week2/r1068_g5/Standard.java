package baekjoon.week.week2.r1068_g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Standard {
    static int n, k, root = -1;
    static ArrayList<Integer>[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        tree = new ArrayList[n];
        for (int i = 0; i < n; ++i) tree[i] = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; ++i) {
            int p = Integer.parseInt(st.nextToken());
            if (p != -1) tree[p].add(i);
            else root = i;
        }

        k = Integer.parseInt(br.readLine());

        int ans = root == k ? 0 : dfs(root);
        System.out.println(ans);
    }

    static int dfs(int here) {
        // 트리 순회할 때는 visited 필요 없음
        int leafCnt = 0;
        for (int there : tree[here]) {
            if (there == k) continue;
            leafCnt += dfs(there);
        }

        return leafCnt == 0 ? 1 : leafCnt;
    }

}
