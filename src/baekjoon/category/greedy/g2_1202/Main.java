package baekjoon.category.greedy.g2_1202;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n, k;
    static int[] bags;
    static int[][] jewels;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        // [mass, value]
        jewels = new int[n][2];
        bags = new int[k];

        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            jewels[i][0] = Integer.parseInt(st.nextToken());
            jewels[i][1] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(jewels, Comparator.comparingInt(j -> j[0]));

        for (int i = 0; i < k; ++i) bags[i] = Integer.parseInt(br.readLine());
        Arrays.sort(bags);

        long answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        int j = 0;
        for (int capacity : bags) {
            while (j < n && capacity >= jewels[j][0]) pq.add(jewels[j++][1]);
            if (!pq.isEmpty()) answer += pq.poll();
        }
        System.out.println(answer);
    }
}
