package baekjoon.gpt.topology_sort.g2_2637;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class TopologySort {
    static int n;
    static ArrayList<int[]>[] graph;
    static int[] indegree;

    static int[][] needCnt;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; ++i) graph[i] = new ArrayList<>();
        indegree = new int[n + 1];
        needCnt = new int[n + 1][n + 1];

        int m = Integer.parseInt(br.readLine());

        StringTokenizer st;
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y= Integer.parseInt(st.nextToken());
            int k= Integer.parseInt(st.nextToken());

            graph[y].add(new int[]{x, k});
            indegree[x]++;
        }

        ArrayList<Integer> base = new ArrayList<>();
        ArrayDeque<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= n; ++i) {
            if (indegree[i] == 0) {
                base.add(i);
                q.add(i);
                needCnt[i][i] = 1;
            }
        }

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int[] next : graph[cur]) {
                int nextIdx = next[0];
                int nextCnt = next[1];

                indegree[nextIdx]--;

                for (Integer b : base) {
                    needCnt[nextIdx][b] += needCnt[cur][b] * nextCnt;
                }

                if (indegree[nextIdx] == 0) q.add(nextIdx);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (Integer b : base) {
            sb.append(b).append(' ').append(needCnt[n][b]).append('\n');
        }
        System.out.println(sb);
    }
}
