package baekjoon.category.greedy.g2_1202;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main2 {
    static int n, k;
    static TreeMap<Integer, Integer> bags;
    static int[][] jewels;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        // [mass, value]
        jewels = new int[n][2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            jewels[i][0] = Integer.parseInt(st.nextToken());
            jewels[i][1] = Integer.parseInt(st.nextToken());
        }
        // value 기준 내림차순
        Arrays.sort(jewels, (j1, j2) -> Integer.compare(j2[1], j1[1]));

        // 각 무게별 가방이 몇개인지 무게 오름차순 정렬
        bags = new TreeMap<>();
        for (int i = 0; i < k; i++) {
            int capacity = Integer.parseInt(br.readLine());
            bags.put(capacity, bags.getOrDefault(capacity, 0) + 1);
        }

        long ans = 0;
        for (int[] jewel : jewels) {
            int mass = jewel[0];

            Integer maxAvailBagCap = bags.ceilingKey(mass);
            if (maxAvailBagCap == null) continue;

            int cnt = bags.get(maxAvailBagCap);
            if (cnt == 1) bags.remove(maxAvailBagCap);
            else bags.put(maxAvailBagCap, cnt - 1);

            ans += jewel[1];
        }

        System.out.println(ans);

    }
}
