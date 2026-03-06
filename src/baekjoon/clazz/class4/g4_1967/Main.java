package baekjoon.clazz.class4.g4_1967;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int n, maxDiameter;
    static ArrayList<int[]>[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        tree = new ArrayList[n + 1];
        for (int i = 0; i <= n; ++i) tree[i] = new ArrayList<>();

        StringTokenizer st;
        for (int i = 0; i < n - 1; ++i) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            tree[p].add(new int[]{c, w});
        }

        dfs(1);

        System.out.println(maxDiameter);
    }

    static int dfs(int here) {
        int best1 = 0, best2 = 0;
        for (int[] there : tree[here]) {
            int child = there[0];
            int weight = there[1];

            int len = dfs(child) + weight;
            if (len > best1) {
                best2 = best1;
                best1 = len;
            } else if (len > best2) {
                best2 = len;
            }
        }

        maxDiameter = Math.max(maxDiameter, best1 + best2);
        return best1;
    }
}
