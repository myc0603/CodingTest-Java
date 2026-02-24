package baekjoon.week.week1.c2979_b2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[] fare = new int[4];
        st = new StringTokenizer(br.readLine());
        fare[1] = Integer.parseInt(st.nextToken());
        fare[2] = Integer.parseInt(st.nextToken());
        fare[3] = Integer.parseInt(st.nextToken());

        int[] cnt = new int[102];
        for (int i = 0; i < 3; ++i) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            for (int j = s; j < e; ++j) ++cnt[j];
        }

        int sum = 0;
        for (int i = 0; i <= 100; ++i) {
            sum += fare[cnt[i]] * cnt[i];
        }

        System.out.println(sum);
    }
}
