package baekjoon.category.greedy.g2_1781;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] hw;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        // [deadline, award]
        hw = new int[n][2];

        StringTokenizer st;
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            hw[i][0] = Integer.parseInt(st.nextToken());
            hw[i][1] = Integer.parseInt(st.nextToken());
        }
        // 데드라인 기준 내림차순
        Arrays.sort(hw, (h1, h2) -> Integer.compare(h2[0], h1[0]));

        int ans = 0;
        int hwIdx = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (int day = n; day >= 1; --day) {
            while (hwIdx < n && day == hw[hwIdx][0]) pq.add(hw[hwIdx++][1]);
            if (!pq.isEmpty()) ans += pq.poll();
        }

        System.out.println(ans);
    }
}
