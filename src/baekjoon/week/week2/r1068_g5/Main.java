package baekjoon.week.week2.r1068_g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int n, k, cnt;
    static ArrayList<Integer>[] tree;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        tree = new ArrayList[n];
        for (int i = 0; i < n; ++i) tree[i] = new ArrayList<>();

        visited = new boolean[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] parents = new int[n];
        for (int i = 0; i < n; ++i) {
            parents[i] = Integer.parseInt(st.nextToken());
            if (parents[i] == -1) continue;
            tree[parents[i]].add(i);
        }
        k = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; ++i) {
            if (tree[i].isEmpty()) ++cnt;
        }

        // k 삭제
        dfs(k);

        // k의 부모가 리프노드가 될 수 있음
        int p = parents[k];
        if (p != -1 && tree[p].size() == 1) ++cnt;

        System.out.println(cnt);
    }

    private static void dfs(int here) {
        visited[here] = true;
        if (tree[here].isEmpty()) --cnt;

        for (int there : tree[here]) {
            if (visited[there]) continue;
            dfs(there);
        }
    }
}
