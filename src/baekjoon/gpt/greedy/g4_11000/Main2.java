package baekjoon.gpt.greedy.g4_11000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main2 {
    static int n;
    static int[][] arr;
    static int[] classrooms;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        arr = new int[n][2];
        classrooms = new int[n];

        StringTokenizer st;
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr, Comparator.comparingInt(arr -> arr[1]));

        int cnt = 0;
        for (int i = 0; i < n; ++i) {
            int j = 0;
            while (classrooms[j] > arr[i][0]) ++j;
            classrooms[j] = arr[i][1];
            if (j >= cnt) ++cnt;
        }
        System.out.println(cnt);
    }
}
