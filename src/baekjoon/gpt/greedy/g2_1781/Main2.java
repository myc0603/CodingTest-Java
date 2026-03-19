package baekjoon.gpt.greedy.g2_1781;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main2 {
    static int n;
    static int[][] hws;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        // [deadline, value]
        hws = new int[n][2];

        StringTokenizer st;
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            hws[i][0] = Integer.parseInt(st.nextToken());
            hws[i][1] = Integer.parseInt(st.nextToken());
        }
        // 데드라인 기준 오른차순 정렬
        Arrays.sort(hws, Comparator.comparingInt(h -> h[0]));

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int[] hw : hws) {
            int deadline = hw[0];
            int value = hw[1];

            pq.add(value);
            while (pq.size() > deadline) pq.poll();
        }

        int ans = 0;
        while (!pq.isEmpty()) ans += pq.poll();
        System.out.println(ans);
    }
}
