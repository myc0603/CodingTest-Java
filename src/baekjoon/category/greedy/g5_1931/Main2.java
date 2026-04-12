package baekjoon.category.greedy.g5_1931;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main2 {
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
            if (a1[0] == a2[0]) return Integer.compare(a2[1], a1[1]);
            return Integer.compare(a2[0], a1[0]);
        });

        int cnt = 1;
        int curStart = arr[0][0];
        for (int i = 1; i < n; ++i) {
            if (arr[i][1] <= curStart) {
                ++cnt;
                curStart = arr[i][0];
            }
        }

        System.out.println(cnt);
    }
}
