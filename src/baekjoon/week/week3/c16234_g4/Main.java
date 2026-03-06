package baekjoon.week.week3.c16234_g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int n, l, r, visitMark = 1;
    static int[] a, visited, dIdx;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        a = new int[n * n];
        visited = new int[n * n];


        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                a[i * n + j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean moved = true;
        while (moved) {
            moved = false;
            for (int i = 0; i < n * n; ++i) {
                if (visited[i] == visitMark) continue;
                moved = bfs(i) || moved ;
            }
            ++visitMark;
        }

        System.out.println(visitMark - 2);
    }

    static boolean bfs(int sIdx) {
        ArrayList<Integer> union = new ArrayList<>();
        int total = 0;
        ArrayDeque<Integer> q = new ArrayDeque<>();
        visited[sIdx] = visitMark;
        q.add(sIdx);

        while (!q.isEmpty()) {
            int cur = q.poll();
            union.add(cur);
            total += a[cur];

            int cy = cur / n;
            int cx = cur % n;

            for (int i = 0; i < 4; i++) {
                int ny = cy + dy[i];
                int nx = cx + dx[i];

                if (ny < 0 || n <= ny || nx < 0 || n <= nx) continue;

                int next = ny * n + nx;
                if (visited[next] == visitMark) continue;

                int diff = Math.abs(a[cur] - a[next]);
                if (l <= diff && diff <= r) {
                    visited[next] = visitMark;
                    q.add(next);
                }
            }
        }

        // 이동 안했으면 return false;
        if (union.size() > 1) return false;

        // 인구 이동
        int newCnt = total / union.size();
        for (int country : union) {
            a[country] = newCnt;
        }
        return true;
    }

}
