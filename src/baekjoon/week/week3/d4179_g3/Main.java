package baekjoon.week.week3.d4179_g3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int n, m, jhStart;
    static int[] a, time;
    static ArrayList<Integer> fires = new ArrayList<>();
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        a = new int[n * m];
        time = new int[n * m];

        for (int i = 0; i < n; ++i) {
            String line = br.readLine();
            for (int j = 0; j < m; ++j) {
                int idx = i * m + j;
                char c = line.charAt(j);
                if (c == 'F') {
                    a[idx] = 1;
                    fires.add(idx);
                }
                if (c == 'J') jhStart = i * m + j;
                if (c == '#') a[idx] = -1;
            }
        }

        for (int fire : fires) fireBfs(fire);
        int ans = jhBfs();

        if (ans == -1) System.out.println("IMPOSSIBLE");
        else System.out.println(ans);
    }

    static void fireBfs(int start) {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(start);
        a[start] = 1;

        while (!q.isEmpty()) {
            int cur = q.poll();
            int y = cur / m;
            int x = cur % m;

            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];

                if (!inMap(ny, nx)) continue;

                int next = ny * m + nx;
                if (a[next] == -1) continue;

                if (a[next] == 0 || a[next] > a[cur] + 1) {
                    a[next] = a[cur] + 1;
                    q.add(next);
                }
            }
        }
    }

    static int jhBfs() {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        time[jhStart] = 1;
        q.push(jhStart);

        while (!q.isEmpty()) {
            int cur = q.poll();
            int y = cur / m;
            int x = cur % m;

            if (y == 0 || y == n - 1 || x == 0 || x == m - 1) return time[cur];

            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];

                // map을 벗어나게 될 때는 위에서 먼저 return이 됨
//                if (!inMap(ny, nx)) continue;

                int next = ny * m + nx;
                if (a[next] == -1) continue;
                if (time[next] > 0) continue;

                if (a[next] != 0 && a[next] <= time[cur] + 1) continue;
                q.add(next);
                time[next] = time[cur] + 1;
            }

        }

        return -1;
    }

    static boolean inMap(int y, int x) {
        return 0 <= y && y < n && 0 <= x && x < m;
    }
}
