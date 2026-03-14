package baekjoon.gpt.greedy.g5_1931;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        arr = new int[n][2];

        StringTokenizer st;
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr, (a1, a2) -> {
            if (a1[1] == a2[1]) return Integer.compare(a1[0], a2[0]);
            return Integer.compare(a1[1], a2[1]);
        });

        int cnt = 1;
        int curEnd = arr[0][1];
        for (int i = 1; i < n; ++i) {
            int s = arr[i][0];

            if (s >= curEnd) {
                ++cnt;
                curEnd = arr[i][1];
            }
        }

        System.out.println(cnt);
    }
}
