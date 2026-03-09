package baekjoon.week.week3.i17071_p5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main2 {
    static int n, k, curTime, MAX = 500000;
    static int[] time = new int[MAX + 1];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        if (n == k) {
            System.out.println(0);
            return;
        }

        Arrays.fill(time, -1);

        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(n);
        time[n] = 0;

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int next : new int[]{cur + 1, cur - 1, cur * 2}) {
                if (next < 0 || next > MAX) continue;
                if (time[next] == time[cur] + 1) continue;
                time[next] = time[cur] + 1;
                q.add(next);

                if (curTime < time[next]) {
//                    ++curTime;
                    curTime = time[next];
                    k += curTime;
                    if (k > MAX) {
                        System.out.println(-1);
                        return;
                    }
                }

                System.out.println("cur = " + cur + ", time[cur] = " + time[cur]);
                System.out.println("next = " + next + ", k = " + k);
                System.out.println("curTime = " + curTime + ", time[next] = " + time[next]);
                System.out.println();
                if (k >= 20) return;

                if (next == k) {
                    System.out.println(time[k]);
                    return;
                }
            }
        }
    }
}
