package baekjoon.week.week3.k3197_p5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 0-1 BFS + Dijkstra
public class Main3 {
    static final int INF = 1_000_000_000;
    static int n, m;
    static char[][] a;
    static boolean[][] visited;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    static int[][] meltDays, dist;
    static int[] swan1, swan2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        a = new char[n][m];
        visited = new boolean[n][m];
        meltDays = new int[n][m];
        dist = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(meltDays[i], INF);
            Arrays.fill(dist[i], INF);
        }

        ArrayDeque<int[]> dq = new ArrayDeque<>();
        for (int i = 0; i < n; ++i) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                a[i][j] = line.charAt(j);
                if (a[i][j] == '.' || a[i][j] == 'L') {
                    dq.offerFirst(new int[]{i, j});
                    meltDays[i][j] = 0;
                }
                if (a[i][j] == 'L') {
                    if (swan1 == null) swan1 = new int[]{i, j};
                    else swan2 = new int[]{i, j};
                }
            }
        }

        buildMeltDays(dq);
        dijkstra();

        System.out.println(dist[swan2[0]][swan2[1]]);
    }

    static void buildMeltDays(ArrayDeque<int[]> dq) {
        while (!dq.isEmpty()) {
            int[] cur = dq.pollFirst();
            int y = cur[0], x = cur[1];

            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];

                if (!inMap(ny, nx)) continue;

                int w = a[ny][nx] == 'X' ? 1 : 0;
                if (meltDays[ny][nx] == INF || meltDays[ny][nx] > meltDays[y][x] + w) {
                    meltDays[ny][nx] = meltDays[y][x] + w;

                    if (w == 0) dq.offerFirst(new int[]{ny, nx});
                    else dq.offerLast(new int[]{ny, nx});
                }
            }
        }
    }

    static boolean inMap(int y, int x) {
        return 0 <= y && y < n && 0 <= x && x < m;
    }

    static class State {
        int y, x, dist;

        public State(int y, int x, int dist) {
            this.y = y;
            this.x = x;
            this.dist = dist;
        }
    }

    static void dijkstra() {
        PriorityQueue<State> pq = new PriorityQueue<>(Comparator.comparingInt(s -> s.dist));
        dist[swan1[0]][swan1[1]] = 0;
        pq.offer(new State(swan1[0], swan1[1], 0));

        while (!pq.isEmpty()) {
            State cur = pq.poll();

            if (cur.dist > dist[cur.y][cur.x]) continue;

            for (int i = 0; i < 4; i++) {
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];

                if (!inMap(ny, nx)) continue;

                int newDist = Math.max(dist[cur.y][cur.x], meltDays[ny][nx]);
                if (dist[ny][nx] > newDist) {
                    dist[ny][nx] = newDist;
                    pq.offer(new State(ny, nx, newDist));
                }
            }
        }
    }

}
