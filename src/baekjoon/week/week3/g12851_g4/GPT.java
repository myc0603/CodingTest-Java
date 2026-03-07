package baekjoon.week.week3.g12851_g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class GPT {
    static int n, k;
    static int[] dist = new int[100001];
    static int[] ways = new int[100001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        if (n == k) {
            System.out.println(0);
            System.out.println(1);
            return;
        }

        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(n);
        dist[n] = 1;
        ways[n] = 1;

        while (!q.isEmpty()) {
            int cur = q.poll();

            int[] nexts = {2 * cur, cur + 1, cur - 1};
            for (int next : nexts) {
                if (next < 0 || next > 100000) continue;

                if (dist[next] == 0) {
                    dist[next] = dist[cur] + 1;
                    ways[next] = ways[cur];
                    q.add(next);
                } else if (dist[next] == dist[cur] + 1) {
                    // ways 수만 갱신하고 굳이 큐에 넣지 않는다.
                    // 똑같은 숫자를 여러 번 중복해서 큐에 넣을 필요 없음
                    ways[next] += ways[cur];
                }

                // dist[next] != 0 이지만 dist[next] < dist[cur] + 1의 경우에는 아무것도 안 함 -> 이건 내 풀이도 그럼
            }
        }

        System.out.println(dist[k] - 1);
        System.out.println(ways[k]);
    }
}
