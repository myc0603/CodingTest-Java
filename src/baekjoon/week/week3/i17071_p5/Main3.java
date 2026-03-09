package baekjoon.week.week3.i17071_p5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main3 {
    static int n, k;
    static int MAX = 500000;
    static int[] dsTime = new int[MAX + 1];
    static boolean[][] visited = new boolean[2][MAX + 1];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        // 동생의 위치별 시간 기록
        Arrays.fill(dsTime, -1);
        for (int t = 0; k <= MAX; ++t, k+= t) {
            dsTime[k] = t;
        }

        // 수빈 bfs
        ArrayDeque<Integer> q = new ArrayDeque<>();
        visited[0][n] = true;
        q.add(n);

        int sbTime = 0;
        while (!q.isEmpty()) {
            int qSize = q.size();
            for (int i = 0; i < qSize; ++i) {
                int cur = q.poll();

                if (visited[sbTime % 2][cur] && sbTime <= dsTime[cur] && sbTime % 2 == dsTime[cur] % 2) {
                    // dsTime[cur] 시점에 cur 에서 만나는 건 맞지만
                    // 이 시점이 최솟값이라는 보장이 없음
                    System.out.println(dsTime[cur]);
                    return;
                }

                for (int next : new int[]{cur - 1, cur + 1, cur * 2}) {
                    if (next < 0 || MAX < next) continue;
                    if (visited[(sbTime + 1) % 2][next]) continue;

                    visited[(sbTime + 1) % 2][next] = true;
                    q.add(next);
                }
            }

            ++sbTime;
        }

        System.out.println(-1);
    }
}
