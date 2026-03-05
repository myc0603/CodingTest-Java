package baekjoon.clazz.class4.s2_15666;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main2 {
    static int n, r;
    static int[] origin, result;

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        origin = new int[n];
        result = new int[r];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; ++i) {
            origin[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(origin);

        comb(0, 0);

        System.out.println(sb);
    }

    static void comb(int depth, int start) {
        if (depth == r) {
            for (int x : result) sb.append(x).append(' ');
            sb.append('\n');
            return;
        }

        for (int i = start; i < origin.length; ++i) {
            if (i != 0 && origin[i - 1] == origin[i]) continue;
//            if (i != start && origin[i - 1] == origin[i]) continue;

            result[depth] = origin[i];
            comb(depth + 1, i);
        }
    }
}
