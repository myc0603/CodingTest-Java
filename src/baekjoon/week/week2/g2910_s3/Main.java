package baekjoon.week.week2.g2910_s3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n, c;
    static Map<Integer, Integer> cntMap = new HashMap<>();
    static Map<Integer, Integer> idxMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; ++i) {
            int t = Integer.parseInt(st.nextToken());
            cntMap.put(t, cntMap.getOrDefault(t, 0) + 1);
            idxMap.putIfAbsent(t, i);
        }

        ArrayList<ArrayList<Integer>> arr = new ArrayList<>();
        for (Integer key : idxMap.keySet()) {
            ArrayList<Integer> t = new ArrayList<>();
            t.add(key);
            t.add(cntMap.get(key));
            t.add(idxMap.get(key));
            arr.add(t);
        }

        arr.sort((i1, i2) -> {
            int cnt1 = i1.get(1);
            int cnt2 = i2.get(1);
            if (cnt1 == cnt2) {
                return Integer.compare(i1.get(2), i2.get(2));
            }
            return -1 * Integer.compare(cnt1, cnt2);
        });

        StringBuilder sb = new StringBuilder();
        for (ArrayList<Integer> value : arr) {
            int cnt = value.get(1);
            for (int i = 0; i < cnt; ++i) {
                sb.append(value.get(0)).append(' ');
            }
        }

        System.out.println(sb);
    }
}
