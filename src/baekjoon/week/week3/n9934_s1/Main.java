package baekjoon.week.week3.n9934_s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int k;
    static ArrayList<Integer>[] levels;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());

        levels = new ArrayList[k + 1];
        for (int i = 1; i <= k; ++i) levels[i] = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int orderSize = (1 << k) - 1;
        for (int i = 1; i <= orderSize; ++i) {
            int num = Integer.parseInt(st.nextToken());
            levels[getLevel(i)].add(num);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = k; i >= 1; --i) {
            for (Integer x : levels[i]) {
                sb.append(x).append(' ');
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }

    static int getLevel(int i) {
        int level = 1;
        while (i % 2 == 0) {
            i /= 2;
            ++level;
        }
        return level;
    }
}
