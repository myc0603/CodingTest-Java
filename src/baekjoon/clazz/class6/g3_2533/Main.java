package baekjoon.clazz.class6.g3_2533;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static ArrayList<Integer>[] tree;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        tree = new ArrayList[n + 1];
        for (int i = 1; i <= n; ++i) tree[i] = new ArrayList<>();

        visited = new boolean[n + 1];

        StringTokenizer st;
        for (int i = 0; i < n - 1; ++i) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            tree[u].add(v);
            tree[v].add(u);
        }

        // 임의의 점을 root로 두고 탐색
        int[] result = dfs(1);
        System.out.println(Math.min(result[0], result[1]));
    }

    static int[] dfs(int cur) {
        visited[cur] = true;
        int isEdCnt = 1;
        int isNotEdCnt = 0;

        for (int next : tree[cur]) {
            if (visited[next]) continue;

            int[] childEdResult = dfs(next);
            isEdCnt += Math.min(childEdResult[0], childEdResult[1]);
            isNotEdCnt += childEdResult[0];
        }

        return new int[]{isEdCnt, isNotEdCnt};
    }
}
