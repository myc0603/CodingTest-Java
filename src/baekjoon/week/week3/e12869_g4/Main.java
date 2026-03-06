package baekjoon.week.week3.e12869_g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int[] scvs = new int[3];
    static int[][] attacks = {
            {9, 3, 1}, {3, 9, 1},
            {9, 1, 3}, {1, 9, 3},
            {3, 1, 9}, {1, 3, 9}
    };
    static int[][][] dist = new int[61][61][61];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            scvs[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(scvs);
        bfs();

        System.out.println(dist[0][0][0] - 1);
    }

    static void bfs() {
        Queue<int[]> q = new ArrayDeque<>();
        dist[scvs[2]][scvs[1]][scvs[0]] = 1;
        q.add(new int[]{scvs[2], scvs[1], scvs[0]});

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            int iterations = n == 3 ? 6 : (n == 2 ? 2 : 1);
            for (int i = 0; i < iterations; i++) {
                int scv1 = Math.max(0, cur[0] - attacks[i][0]);
                int scv2 = Math.max(0, cur[1] - attacks[i][1]);
                int scv3 = Math.max(0, cur[2] - attacks[i][2]);

                int[] next = {scv1, scv2, scv3};
                Arrays.sort(next);
                int a = next[2];
                int b = next[1];
                int c = next[0];

                if (dist[a][b][c] != 0) continue;

                dist[a][b][c] = dist[cur[0]][cur[1]][cur[2]] + 1;
                if (a == 0 && b == 0 && c == 0) return;

                q.add(new int[]{a, b, c});
            }
        }
    }
}
