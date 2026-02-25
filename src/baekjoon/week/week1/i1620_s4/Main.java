package baekjoon.week.week1.i1620_s4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        String[] arr = new String[n + 1];
        Map<String, Integer> map = new HashMap<>();

        for (int i = 1; i <= n; ++i) {
            String s = br.readLine();
            arr[i] = s;
            map.put(s, i);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= m; ++i) {
            String s = br.readLine();
            char c = s.charAt(0);

            if ('0' <= c && c <= '9') {
                int key = Integer.parseInt(s);
                sb.append(arr[key]).append('\n');
            } else {
                sb.append(map.get(s)).append('\n');
            }
        }

        System.out.println(sb);
    }
}
