package baekjoon.week.week2.f2828_s5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m, j, dist, pos = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        j = Integer.parseInt(br.readLine());
        for (int i = 0; i < j; ++i) {
            int apple = Integer.parseInt(br.readLine());
            if (apple > pos + m -1) {
                int t = apple - (pos + m - 1);
                pos += t;
                dist += t;
            } else if (apple < pos) {
                int t = pos - apple;
                pos -= t;
                dist += t;
            }
        }

        System.out.println(dist);
    }
}
