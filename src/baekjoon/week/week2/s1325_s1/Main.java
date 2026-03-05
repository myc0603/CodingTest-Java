package baekjoon.week.week2.s1325_s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static ArrayList<Integer>[] tree;
//    static int[] childCnt;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        tree = new ArrayList[n + 1];
        for (int i = 0; i <= n; ++i) tree[i] = new ArrayList<>();
        visited = new boolean[n + 1];
//        childCnt = new int[n + 1];

        for (int i = 0; i < m; ++i) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            tree[b].add(a);
        }

        ArrayList<Integer> arr = new ArrayList<>();
        int maxCnt = 0;
        for (int i = 1; i <= n; ++i) {
//            System.out.println("i = " + i);
            int cnt = dfs(i);
//            System.out.println("cnt = " + cnt);
//            System.out.println();
            if (cnt > maxCnt) {
                maxCnt = cnt;
                arr = new ArrayList<>();
                arr.add(i);
            } else if (cnt == maxCnt) {
                arr.add(i);
            }
            Arrays.fill(visited, false);
        }
        arr.sort(null);

        StringBuilder sb = new StringBuilder();
        for (int x : arr) sb.append(x).append(' ');
        System.out.println(sb);
    }

    private static int dfs(int here) {
        visited[here] = true;
//        if (childCnt[here] != 0) return childCnt[here];

        int cnt = 1;
//        System.out.println("tree[" + here + "] = " + tree[here]);
        for (int there : tree[here]) {
//            System.out.println("there = " + there);
            if (visited[there]) continue;
            cnt += dfs(there);
        }
//        childCnt[here] = cnt;
        return cnt;
    }
}
