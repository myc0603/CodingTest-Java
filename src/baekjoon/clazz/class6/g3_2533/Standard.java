package baekjoon.clazz.class6.g3_2533;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Standard {
    static int n;
    static ArrayList<Integer>[] tree;
    static int[] parent, order;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        tree = new ArrayList[n + 1];
        for (int i = 1; i <= n; ++i) tree[i] = new ArrayList<>();

        parent = new int[n + 1];
        order = new int[n];

        dp = new int[2][n + 1];
        Arrays.fill(dp[0], -1);
        Arrays.fill(dp[1], -1);

        StringTokenizer st;
        for (int i = 0; i < n - 1; ++i) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            tree[u].add(v);
            tree[v].add(u);
        }

        // 1을 root로 사용
        buildParentAndOrder(1);
        solve();

        System.out.println(Math.min(dp[0][1], dp[1][1]));
    }

    static void buildParentAndOrder(int root) {
        ArrayDeque<Integer> stk = new ArrayDeque<>();
        stk.push(root);
        parent[root] = -1;
//        visited[root] = true;

        int idx = 0;
        while (!stk.isEmpty()) {
            int cur = stk.pop();
            order[idx++] = cur;

            for (int next : tree[cur]) {
                // parent 배열을 visited 대용으로 사용 가능
                if (next == parent[cur]) continue;
//                if (visited[next]) continue;

//                visited[next] = true;
                parent[next] = cur;
                stk.push(next);
            }
        }
    }

    static void solve() {
        for (int i = order.length - 1; i >= 0; --i) {
            int cur = order[i];

            dp[0][cur] = 1;
            dp[1][cur] = 0;

            for (int child : tree[cur]) {
                if (child == parent[cur]) continue;

                dp[0][cur] += Math.min(dp[0][child], dp[1][child]);
                dp[1][cur] += dp[0][child];
            }
        }
    }

}
