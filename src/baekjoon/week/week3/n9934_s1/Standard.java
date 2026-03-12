package baekjoon.week.week3.n9934_s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Standard {
    static int k;
    static int[] orders;
    static ArrayList<Integer>[] levels;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());

        levels = new ArrayList[k];
        for (int i = 0; i < k; i++) levels[i] = new ArrayList<>();

        int orderSize = (1 << k) - 1;
        orders = new int[orderSize];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < orderSize; ++i) {
            orders[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0, 0, orderSize - 1);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < k; ++i) {
            for (Integer x : levels[i]) {
                sb.append(x).append(' ');
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }

    static void dfs(int depth, int start, int end) {
//        if (depth == k) return;
        if (start > end) return;


        int mid = (start + end) / 2;
        int subRoot = orders[mid];
        levels[depth].add(subRoot);

        dfs(depth + 1, start, mid - 1);
        dfs(depth + 1, mid + 1, end);
    }
}
