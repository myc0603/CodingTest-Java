package baekjoon.week.week3.a15686_g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int n, m, dist = Integer.MAX_VALUE;
    static ArrayList<Integer> chickens = new ArrayList<>();
    static ArrayList<Integer> homes = new ArrayList<>();
    static int[] notClosedChickens;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        notClosedChickens = new int[m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int t = Integer.parseInt(st.nextToken());
                if (t == 1) homes.add(i * n + j);
                if (t == 2) chickens.add(i * n + j);
            }
        }

        comb(0, 0);

        System.out.println(dist);
    }

    static void comb(int depth, int start) {
        if (depth == m) {
            calc();
            return;
        }

        for (int i = start; i < chickens.size(); ++i) {
            notClosedChickens[depth] = chickens.get(i);
            comb(depth + 1, i + 1);
        }
    }

    static void calc() {
        int result = 0;
        for (int home : homes) {
            int hy = home / n;
            int hx = home % n;

            int t = 1000;
            for (int c : notClosedChickens) {
                int cy = c / n;
                int cx = c % n;

                t = Math.min(t, Math.abs(hy - cy) + Math.abs(hx - cx));
            }
            result += t;
            if (result >= dist) return;
        }
        dist = Math.min(dist, result);
    }
}
