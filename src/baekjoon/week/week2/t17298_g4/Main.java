package baekjoon.week.week2.t17298_g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] arr, nge;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        nge = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; ++i) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.fill(nge, -1);

        ArrayDeque<Integer> stk = new ArrayDeque<>();
        for (int i = 0; i < n; ++i) {
            int cur = arr[i];
            while (!stk.isEmpty() && arr[stk.peek()] < cur) {
                 int idx = stk.pop();
                 nge[idx] = cur;
            }
            stk.push(i);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) sb.append(nge[i]).append(' ');
        System.out.println(sb);
    }
}
