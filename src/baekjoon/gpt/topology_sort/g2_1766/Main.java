package baekjoon.gpt.topology_sort.g2_1766;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// fail
public class Main {
    static int n, m;
    static ArrayList<Integer>[] graph;
    static int[] indegree;

    static int[] next;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; ++i) graph[i] = new ArrayList<>();
        indegree = new int[n + 1];
        next = new int[n + 1];

        for (int i = 0; i < m; ++i) {
            st = new StringTokenizer(br.readLine());
            int prev = Integer.parseInt(st.nextToken());
            int next = Integer.parseInt(st.nextToken());

            graph[prev].add(next);
            ++indegree[next];
        }

        ArrayList<Integer> starts = new ArrayList<>();
        for (int i = 1; i <= n; ++i) {
            if (indegree[i] == 0) starts.add(i);
        }

//        next[0] = starts.get(0);
        for (Integer s : starts) {
            ArrayList<Integer> result = topologySort(s);
//            System.out.println("result = " + result);

            int cur = 0;
            for (Integer x : result) {
                while (next[cur] < x && next[cur] != 0) {
                    cur = next[cur];
                }
//                System.out.println("cur = " + cur);
//                System.out.println("next[cur] = " + next[cur]);
//                System.out.println("x = " + x);
//                System.out.println();
                next[x] = next[cur];
                next[cur] = x;

                cur = x;
            }
        }

        StringBuilder sb = new StringBuilder();
        int cur2 = next[0];
        while (cur2 != 0) {
//            System.out.println("cur2 = " + cur2);
            sb.append(cur2).append(' ');
            cur2 = next[cur2];
        }
        System.out.println(sb);
    }

    static ArrayList<Integer> topologySort(int start) {
        ArrayList<Integer> result = new ArrayList<>();

        ArrayDeque<Integer> q = new ArrayDeque<>();
        ArrayList<Integer> temp = new ArrayList<>();
        q.add(start);

        while (!q.isEmpty()) {
            int cur = q.poll();
            result.add(cur);

            for (Integer next : graph[cur]) {
                --indegree[next];
                if (indegree[next] == 0) {
                    temp.add(next);
                }
            }

            if (q.isEmpty()) {
                Collections.sort(temp);
                q.addAll(temp);
                temp.clear();
            }
        }

        return result;
    }
}
