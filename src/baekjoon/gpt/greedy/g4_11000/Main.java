package baekjoon.gpt.greedy.g4_11000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

// 시간 초과
public class Main {
    static int n;
    static int[][] arr;
    static boolean[] isAssigned;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        arr = new int[n][2];
        isAssigned = new boolean[n];

        StringTokenizer st;
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr, Comparator.comparingInt(a -> a[1]));

        int cnt = 0;
        while (true) {
            int t = -1;
            for (int i = 0; i < n; ++i) {
                if (isAssigned[i]) continue;
                if (t <= arr[i][0]) {
                    isAssigned[i] = true;
                    t = arr[i][1];
                }

            }

            if (t == -1) break;
            ++cnt;
        }

        System.out.println(cnt);
    }
}
