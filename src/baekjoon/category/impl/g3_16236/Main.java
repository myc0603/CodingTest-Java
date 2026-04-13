package baekjoon.category.impl.g3_16236;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 틀린 이유: 정렬도 bfs로 최단거리를 구해야 함
public class Main {
    static int n;
    static int[][] map;
    static boolean[][] visited;
    static boolean[][] checked;

    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    static int sharkSize = 2;
    static int eatCount = 0;
    static int sy, sx; // 상어 위치
    static int time = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        map = new int[n][n];
        visited = new boolean[n][n];
        checked = new boolean[n][n];

        StringTokenizer st;
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; ++j) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    sy = i;
                    sx = j;
                    map[i][j] = 0; // 의미 없지 않음
                }
            }
        }

        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{sy, sx});
        visited[sy][sx] = true;
        checked[sy][sx] = true;

        // 정렬 방향 바꿔서 ArrayList
        LinkedList<int[]> canEat = new LinkedList<>();
        ArrayList<int[]> sameFishes = new ArrayList<>();
        ArrayList<int[]> biggerFishes = new ArrayList<>();

        while (true) {
            while (!q.isEmpty()) {
                int[] cur = q.poll();
                int y = cur[0], x = cur[1];

                for (int i = 0; i < 4; ++i) {
                    int ny = y + dy[i];
                    int nx = x + dx[i];

                    if (!inMap(ny, nx) || visited[ny][nx]) continue;

                    // 맞나?
                    if (checked[ny][nx]) continue;
                    checked[ny][nx] = true;

                    int fishSize = map[ny][nx];
                    // bigger 체크 필요
                    if (fishSize > sharkSize) {
                        biggerFishes.add(new int[]{ny, nx});
                        continue;
                    }
                    if (fishSize == sharkSize) sameFishes.add(new int[]{ny, nx});
                    if (0 < fishSize && fishSize < sharkSize) canEat.add(new int[]{ny, nx});

                    q.add(new int[]{ny, nx});
                    visited[ny][nx] = true;
                }
            }

            int originSize = sharkSize;
            while (sharkSize == originSize && !canEat.isEmpty()) {
                canEat.sort((f1, f2) -> comparingFish(f1, f2));
                int[] eatenFish = canEat.get(0);
                System.out.println("eatenFish = " + Arrays.toString(eatenFish));

                // 상어 이동
                // 여기가 문제 중간에 큰 물고기에 막혀있는거 고려 안 했던 부분
//                moveTo(eatenFish);
                time += moveTo(eatenFish);

                // 먹기
                eatCount++;
                canEat.remove(0);
                map[eatenFish[0]][eatenFish[1]] = 0; // 이건 의미 없는데

                // 커지기
                if (eatCount == sharkSize) {
                    sharkSize++;
                    eatCount = 0; // 문제 조건이 살짝 애매
                }
            }

            // canEat.isEmpty() == true
            if (sharkSize == originSize) break;

            if (sharkSize > originSize) {
                canEat.addAll(sameFishes);
                sameFishes.clear();
            }

//            for (int i = 0; i < n; ++i) Arrays.fill(visited[i], false);
//            canEat.clear();
//            sameFishes.clear();
//            biggerFishes.clear();
//
//            q.add(new int[]{sy, sx});
//            visited[sy][sx] = true;

            ArrayList<int[]> newBigger = new ArrayList<>();
            for (int[] f : biggerFishes) {
                int biggerSize = map[f[0]][f[1]];
                if (sharkSize == biggerSize) {
                    sameFishes.add(f);
                    q.add(f);
                    visited[f[0]][f[1]] = true;
                    checked[f[0]][f[1]] = true;
                } else if (sharkSize < biggerSize) {
                    newBigger.add(f);
                }
            }

            biggerFishes = newBigger;
        }

        System.out.println(time);
    }

    static int moveTo(int[] fish) {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        int[][] dist = new int[n][n];

        q.add(new int[]{sy, sx});
        dist[sy][sx] = 1;

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            if (cur[0] == fish[0] && cur[1] == fish[1]) {
//                time += dist[fish[0]][fish[1]] - 1;
//                break;

                sy = fish[0];
                sx = fish[1];
                return dist[fish[0]][fish[1]] - 1;
            }

            for (int i = 0; i < 4; ++i) {
                int ny = cur[0] + dy[i];
                int nx = cur[1] + dx[i];

                if (!inMap(ny, nx) || dist[ny][nx] != 0) continue;
                if (map[ny][nx] > sharkSize) continue;

                q.add(new int[]{ny, nx});
                dist[ny][nx] = dist[cur[0]][cur[1]] + 1;
            }
        }

//        sy = fish[0];
//        sx = fish[1];

        return 9999999;
    }

    static boolean inMap(int y, int x) {
        return 0 <= y && y < n && 0 <= x && x < n;
    }

    static int comparingFish(int[] f1, int[] f2) {
        int dist1 = Math.abs(f1[0] - sy) + Math.abs(f1[1] - sx);
        int dist2 = Math.abs(f2[0] - sy) + Math.abs(f2[1] - sx);

        if (dist1 != dist2) return Integer.compare(dist1, dist2);
        if (f1[0] != f2[0]) return Integer.compare(f1[0], f2[0]);
        return Integer.compare(f1[1], f2[1]);
    }
}
