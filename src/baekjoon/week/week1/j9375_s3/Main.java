package baekjoon.week.week1.j9375_s3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < t; ++i) {
            int n = Integer.parseInt(br.readLine());
            HashMap<String, Integer> map = new HashMap<>();

            for (int j = 0; j < n; ++j) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String name = st.nextToken();
                String type = st.nextToken();
                Integer cnt = map.get(type);
                map.put(type, cnt != null ? cnt + 1 : 1);
            }

            int ans = 1;
            for (Integer value : map.values()) {
                ans *= value + 1;
            }

            sb.append(ans - 1).append('\n');
        }

        System.out.println(sb);
    }
}
