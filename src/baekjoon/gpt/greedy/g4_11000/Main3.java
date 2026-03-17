package baekjoon.gpt.greedy.g4_11000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 시간 초과
public class Main3 {
    static int n;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        // [start, end]
        arr = new int[n][2];

        StringTokenizer st;
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        ArrayList<Integer> temp = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            while (!pq.isEmpty() && pq.peek() <= arr[i][0]) {
                temp.add(pq.poll());
            }
            pq.add(arr[i][1]);
            if (!temp.isEmpty()) temp.remove(temp.size() - 1);
            pq.addAll(temp);
            temp.clear();
        }

        System.out.println(pq.size());
    }
}
