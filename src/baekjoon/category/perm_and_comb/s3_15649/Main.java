package baekjoon.category.perm_and_comb.s3_15649;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int r;
    static int[] arr;
    static boolean[] visited;
    static int[] result;

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        arr = new int[n];
        for (int i = 0; i < n; ++i) arr[i] = i + 1;
        visited = new boolean[n];
        result = new int[r];

        perm(0);

        System.out.println(sb);
    }

    static void perm(int depth) {
        if (depth == r) {
            for (int i = 0; i < r; ++i) {
                sb.append(result[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 0; i < n; ++i) {
            if (visited[i]) continue;

            visited[i] = true;
            result[depth] = arr[i];
            perm(depth + 1);
            visited[i] = false;
        }
    }
}
