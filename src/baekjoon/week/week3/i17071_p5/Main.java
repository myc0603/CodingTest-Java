package baekjoon.week.week3.i17071_p5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n, k, curTime, MAX = 500000;
    static int[] time = new int[MAX + 1];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        Arrays.fill(time, -1);

        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(n);
        time[n] = 0;

        while (!q.isEmpty()) {
            int cur = q.poll();

            // 현재 time 확인해서 k 갱신 후 cur와 비교
            // 여기서 사용되는 time[cur]은 오염된 데이터
            // 특정값의 time이 값이 계속 바뀜
            // time = 3 인 cur 값을 꺼냈는데 꺼내기 전에 똑같은 cur 값이 다른 time으로 들어와서
            // 내가 의도한 curTime보다 time[cur]의 값이 크게 되고
            // 그래서 잘못된 타이밍에 k 값이 갱신된다.
            // => cur을 큐에서 꺼낼때 처리하지말고 넣을 대 처리하면 될 듯?
            if (curTime < time[cur]) {
                ++curTime;
                k += curTime;
                if (k > MAX) {
                    time[k] = -1;
                    break;
                }
            }

            System.out.println("cur = " + cur + ", k = " + k);
            if (cur == k) break;

            // debug
//            if (k > 15) return;

            for (int next : new int[]{cur + 1, cur - 1, cur * 2}) {
                if (next < 0 || next > MAX) continue;
//                if (time[next] != -1) continue;
                // 같은 거는 pass
//                if (time[next] == time[cur] + 1) continue;
                time[next] = time[cur] + 1;
                q.add(next);
                System.out.println("\tenqueue: next = " + next + ", time[next}: " + time[next]);
            }

            System.out.println("\t=> q: " + q);
        }

//        System.out.println(curTime);

        System.out.println("curTime = " + curTime);
        System.out.println("time[k] = " + time[k]);
    }
}
