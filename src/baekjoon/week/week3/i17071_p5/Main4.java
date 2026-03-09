package baekjoon.week.week3.i17071_p5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main4 {
    static int n, k;
    static int MAX = 500000;
    static boolean[][] visited = new boolean[2][MAX + 1];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        ArrayDeque<Integer> q = new ArrayDeque<>();
        visited[0][n] = true;
        q.add(n);

        int time = 0;
        while (!q.isEmpty()) {
            if (k > MAX) {
                System.out.println(-1);
                return;
            }

            if (visited[time % 2][k]) {
                System.out.println(time);
                return;
            }

            int qSize = q.size();
            for (int i = 0; i < qSize; ++i) {
                int cur = q.poll();

                for (int next : new int[]{cur - 1, cur + 1, cur * 2}) {
                    if (next < 0 || MAX < next) continue;
                    if (visited[(time + 1) % 2][next]) continue;

                    visited[(time + 1) % 2][next] = true;
                    q.add(next);
                }
            }

            ++time;
            k += time;
        }
    }
}
