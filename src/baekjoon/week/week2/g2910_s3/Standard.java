package baekjoon.week.week2.g2910_s3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Standard {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        Map<Integer, Integer> freq = new LinkedHashMap<>();

        for (int i = 0; i < n; ++i) {
            int x = Integer.parseInt(st.nextToken());
            freq.merge(x, 1, Integer::sum);
        }

        ArrayList<Map.Entry<Integer, Integer>> list = new ArrayList<>(freq.entrySet());

        list.sort((a, b) -> Integer.compare(b.getValue(), a.getValue()));

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, Integer> e : list) {
            int val = e.getKey();
            int cnt = e.getValue();
            for (int i = 0; i < cnt; ++i) sb.append(val).append(' ');
        }

        System.out.println(sb);
    }
}
