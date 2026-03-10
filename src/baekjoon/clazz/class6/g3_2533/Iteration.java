package baekjoon.clazz.class6.g3_2533;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// 시간 초과
public class Iteration {
    static int n;
    static ArrayList<Integer>[] tree;
    static boolean[] visited;
    static int[][] dp;
    static int[] friendsCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        tree = new ArrayList[n + 1];
        for (int i = 1; i <= n; ++i) tree[i] = new ArrayList<>();

        visited = new boolean[n + 1];

        // dp[0][i]: ith node가 ed일 때
        // dp[1][i]: ith node가 ed가 아닐 때
        dp = new int[2][n + 1];
        Arrays.fill(dp[0], -1);
        Arrays.fill(dp[1], -1);

        friendsCnt = new int[n + 1];

        StringTokenizer st;
        for (int i = 0; i < n - 1; ++i) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            tree[u].add(v);
            tree[v].add(u);

            ++friendsCnt[u];
            ++friendsCnt[v];
        }

        ArrayDeque<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= n; ++i) {
            // leaf
            if (friendsCnt[i] == 1) q.add(i);
        }

        while (!q.isEmpty()) {
            int cur = q.poll();
            int parent = updateDp(cur);
            if (parent == -2) q.add(cur);
            else if (parent != -1) q.add(parent);
            // parent == -1일 때는 마지막 root 처리

            if (q.isEmpty()) {
                // 마지막 cur, 즉 root 처리
                System.out.println(Math.min(dp[0][cur], dp[1][cur]));
                return;
            }
        }
    }

    static int updateDp(int cur) {
        int isEdCnt = 1;
        int isNotEdCnt = 0;

        int parentNode = -1;
        for (int next : tree[cur]) {
            if (!visited[next]) {
                if (parentNode != -1) return -2;
                parentNode = next;
                continue;
            }

            isEdCnt += Math.min(dp[0][next], dp[1][next]);
            isNotEdCnt += dp[0][next];
        }

        visited[cur] = true;
        dp[0][cur] = isEdCnt;
        dp[1][cur] = isNotEdCnt;
        return parentNode;
    }
}
