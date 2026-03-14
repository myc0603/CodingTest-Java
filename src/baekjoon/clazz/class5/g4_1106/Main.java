package baekjoon.clazz.class5.g4_1106;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// fail
public class Main {
    static int c, n;
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        c = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        PriorityQueue<int[]> pq = new PriorityQueue<>((Comparator.comparingDouble(p -> (double) p[0] / p[1])));

        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(st.nextToken());
            int cnt = Integer.parseInt(st.nextToken());

            pq.add(new int[]{cost, cnt});
        }

        int totalCost = 0;
        while (!pq.isEmpty() && c > 0) {
            int[] info = pq.poll();
            int cost = info[0], cnt = info[1];

            totalCost += cost * (c / cnt);
            ans = Math.min(ans, totalCost + (c % cnt == 0 ? 0 : cost));

            c %= cnt;
        }

        System.out.println(ans);
    }
}
