package baekjoon.category.impl.g3_16236;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main2 {
//    static final int MAX_DIST = 500;

    static int n;
    static int[][] map;
    static int[][] visited;

    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    static int sharkSize = 2;
    static int eatCount = 0;
    static int sy, sx;
    static int time = 0;

//    static int  minDist = MAX_DIST;
    static ArrayList<int[]> canEat = new ArrayList<>();
    static ArrayDeque<int[]> q = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        map = new int[n][n];
        visited = new int[n][n];
        initVisited();

        StringTokenizer st;
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; ++j) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    sy = i;
                    sx = j;
                    map[i][j] = 0;
                }
            }
        }

        q.add(new int[]{sy, sx});
        visited[sy][sx] = 0;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int y = cur[0], x = cur[1];

            for (int i = 0; i < 4; ++i) {
                int ny = y + dy[i];
                int nx = x + dx[i];

                if (!inMap(ny, nx) || visited[ny][nx] != -1) continue;

                int fishSize = map[ny][nx];
                if (fishSize > sharkSize) continue;
                if (0 < fishSize && fishSize < sharkSize) canEat.add(new int[]{ny, nx});

                q.add(new int[]{ny, nx});
                visited[ny][nx] = visited[y][x] + 1;
            }

            if (q.isEmpty()) eat();
        }

        System.out.println(time);

    }

    static void eat() {
//        System.out.println();
//        System.out.println("eat - canEat size = " + canEat.size());
//        for (int[] fish : canEat) {
//            System.out.println(Arrays.toString(fish));
//        }

        int originalSize = sharkSize;
        while (originalSize == sharkSize && !canEat.isEmpty()) {
            int[][] dist = calcDist();
            canEat.sort((f1, f2) -> comparingFish(f1, f2, dist));

//            System.out.println("sorted canEat with sy = " + sy + ", sx = " + sx);
//            for (int[] f : canEat) {
//                System.out.println(Arrays.toString(f) + ", dist: " + dist[f[0]][f[1]]);
//            }

            int[] eatenFish = canEat.get(canEat.size() - 1);
//            System.out.println("eatenFish = " + Arrays.toString(eatenFish));

            // 상어 이동
            sy = eatenFish[0];
            sx = eatenFish[1];
            time += dist[eatenFish[0]][eatenFish[1]];

            // 먹기
            eatCount++;
            if (eatCount == sharkSize) {
                sharkSize++;
                eatCount = 0;
            }
            map[eatenFish[0]][eatenFish[1]] = 0;
            canEat.remove(canEat.size() - 1);
        }

        if (originalSize == sharkSize) return;

        initVisited();
        canEat.clear();
//        minDist = MAX_DIST;

        q.add(new int[]{sy, sx});
        visited[sy][sx] = 0;
    }

    static boolean inMap(int y, int x) {
        return 0 <= y && y < n && 0 <= x && x < n;
    }

    static void initVisited() {
        for (int i = 0; i < n; ++i) Arrays.fill(visited[i], -1);
    }

    static int comparingFish(int[] f1, int[] f2, int[][] dist) {
        int dist1 = dist[f1[0]][f1[1]];
        int dist2 = dist[f2[0]][f2[1]];
        if (dist1 != dist2) return Integer.compare(dist2, dist1);
        if (f1[0] != f2[0]) return Integer.compare(f2[0], f1[0]);
        return Integer.compare(f2[1], f1[1]);
    }

    static int[][] calcDist() {
        ArrayDeque<int[]> qq = new ArrayDeque<>();
        int[][] dist = new int[n][n];
        for (int i = 0; i < n; ++i) Arrays.fill(dist[i], -1);
        qq.add(new int[]{sy, sx});
        dist[sy][sx] = 0;

        while (!qq.isEmpty()) {
            int[] cur = qq.poll();
            int y = cur[0], x = cur[1];

            for (int i = 0; i < 4; ++i) {
                int ny = y + dy[i];
                int nx = x + dx[i];

                if (!inMap(ny, nx) || visited[ny][nx] == -1 || dist[ny][nx] != -1) continue;

                dist[ny][nx] = dist[y][x] + 1;
                qq.add(new int[]{ny, nx});
            }
        }

        return dist;
    }
}
