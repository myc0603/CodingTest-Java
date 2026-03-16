package baekjoon.clazz.class2.s3_1966;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int t, n, m;
    static int[] arr = new int[100];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            PriorityQueue<Integer> pq = new PriorityQueue<>((a1, a2) -> Integer.compare(a2, a1));
            // [index, priority]
            Queue<int[]> q = new ArrayDeque<>();
            for (int i = 0; i < n; ++i) {
                arr[i] = Integer.parseInt(st.nextToken());
                q.add(new int[]{i, arr[i]});
                pq.add(arr[i]);
            }

            int cnt = 1;
            while (!q.isEmpty() && !pq.isEmpty()) {
                int[] fromQ = q.poll();
                if (fromQ[1] == pq.peek()) {
                    pq.poll();
                    if (fromQ[0] == m) break;
                    ++cnt;
                } else {
                    q.add(fromQ);
                }
            }
            sb.append(cnt).append('\n');
        }

        System.out.println(sb);
    }
}
