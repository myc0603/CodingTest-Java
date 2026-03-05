package baekjoon.week.week2.r1068_g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main2 {
    static int n, k, cnt, root;
    static int[] parents;
    static boolean[] visited;
    static ArrayList<Integer>[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        parents = new int[n];
        visited = new boolean[n];
        tree = new ArrayList[n];
        for (int i = 0; i < n; i++) tree[i] = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            parents[i] = Integer.parseInt(st.nextToken());
            if (parents[i] != -1) tree[parents[i]].add(i);
            else root = i;
        }
        k = Integer.parseInt(br.readLine());
        if (k == root) {
            System.out.println(0);
            return;
        }

        int p = parents[k];
        tree[p].remove(Integer.valueOf(k));

        dfs(root);
        System.out.println(cnt);
    }

    static void dfs(int here) {
        visited[here] = true;
        if (tree[here].isEmpty()) ++cnt;

        for (int there : tree[here]) {
            if (visited[there]) continue;
            dfs(there);
        }
    }
}
