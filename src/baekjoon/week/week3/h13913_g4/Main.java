package baekjoon.week.week3.h13913_g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int n, k;
    static int[] dist = new int[100001];
    static int[] prev = new int[100001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(n);
        dist[n] = 1;
        prev[n] = -1;

        while (!q.isEmpty()) {
            int cur = q.poll();
            if (cur == k) break;

            for (int next : new int[]{cur + 1, cur - 1, cur * 2}) {
                if (next < 0 || 100000 < next) continue;

                if (dist[next] > 0) continue;

                dist[next] = dist[cur] + 1;
                prev[next] = cur;

                q.add(next);
            }
        }

        ArrayList<Integer> route = new ArrayList<>();
        int cur = k;
        while (cur != -1) {
            route.add(cur);
            cur = prev[cur];
        }

        StringBuilder sb = new StringBuilder();
        sb.append(dist[k] - 1).append('\n');
        for (int i = route.size() - 1; i >= 0; --i) {
            sb.append(route.get(i)).append(' ');
        }
        System.out.println(sb);
    }
}
