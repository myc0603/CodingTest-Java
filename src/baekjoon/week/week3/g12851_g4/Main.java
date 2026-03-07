package baekjoon.week.week3.g12851_g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    static int n, k, cnt;
    static int[] dist = new int[100001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(n);
        dist[n] = 1;

        while (!q.isEmpty()) {
            int cur = q.poll();

            // dist[cur]는 무조건 0이 아님
            // dist[k]는 0 일 수도 있는데 같다는 건 dist[k]가 정해졌다는 뜻
            if (dist[cur] == dist[k]) break;

            int[] nexts = {2 * cur, cur + 1, cur - 1};

            // 틀림
//            for (int next : nexts) {
//                if (next < 0 || next > 100000) continue;
//
//                if (next == k) ++cnt;
//                // k로 가는 중간에 같은 값을 같은 거리로 지나치는 경우 개수를 따로 세야 됨
//                // 그래서 큐에 중복되게 넣어야 모두 갯수를 셀 수 있음
//                // 그래서 아래처럼 중복되는 경우인데 더 긴 거리로 도착한 경우만 무시해야 됨
//                if (dist[next] > 0) continue;
//
//                dist[next] = dist[cur] + 1;
//                q.add(next);
//            }

            // 맞음
            for (int next : nexts) {
                if (next < 0 || next > 100000) continue;

                if (dist[next] != 0 && dist[next] < dist[cur] + 1) continue;
                dist[next] = dist[cur] + 1;
                if (next == k) ++cnt;
                q.add(next);
            }
        }

        System.out.println(dist[k] - 1);

        // n과 k가 같은 케이스는 따로
        System.out.println(n == k ? 1 : cnt);
    }
}
