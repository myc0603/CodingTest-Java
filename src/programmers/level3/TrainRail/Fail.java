package programmers.level3.TrainRail;

class Fail {
    int[][] grid;
    int n, m;
    int[] dy = {-1, 0, 1, 0};
    int[] dx = {0, 1, 0, -1};

    int[] rails = {
            0,
            (1 << 1) | (1 << 3),
            (1 << 0) | (1 << 2),
            (1 << 0) | (1 << 1) | (1 << 2) | (1 << 3),
            (1 << 0) | (1 << 3),
            (1 << 0) | (1 << 1),
            (1 << 1) | (1 << 2),
            (1 << 2) | (1 << 3),
    };

    public static void main(String[] args) {
        System.out.println(new Fail().solution(new int[][]{
                // o
//                {1, 0, -1},
//                {0, 0, 7},
//                {0, 0, 2}

                // o
//                {1, 0, 0, 0, 0, -1, -1},
//                {-1, 0, 0, 1, 0, 0, 1}

                // o
//                {1, 0, 0, 0, 0},
//                {0, 0, 3, 0, 2},
//                {0, 0 ,0, 0, 2}

                // x - 644 -> 1024
                {1, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 1}

                // o
//                {1, 7},
//                {0, 2}

                // x - 0 -> 8
//                {1, -1, 0, 0},
//                {-1 ,0, 0, 0},
//                {0, 0, 0, -1},
//                {0, 0, -1, 1}
        }));
    }

    public int solution(int[][] g) {
        grid = g;
        n = g.length;
        m = g[0].length;

        return dfs(0, 0);
    }

    int dfs(int i, int j) {
        if (i == n - 1 && j == m - 1) return isValidWholeTrack() ? 1 : 0;
        if (j == m) return dfs(i + 1, 0);
//        if (i == n) return isValidWholeTrack() ? 1 : 0;
//        if (i == n) return 1;
        if (grid[i][j] != 0) {
            int railIdx = grid[i][j] == -1 ? 0 : grid[i][j];
            if (canPut(i, j, rails[railIdx], true)) return dfs(i, j + 1);
            return 0;
        }

        int ret = 0;
        for (int k = 0; k <= 7; ++k) {
            boolean canPut = canPut(i, j, rails[k], false);
            if (canPut) {
                grid[i][j] = k;
                ret += dfs(i, j + 1);
                grid[i][j] = 0;
            }
        }

        return ret;
    }

    // 이것만 Solution 버전으로 하면 정답.
    boolean isValidWholeTrack() {
        int railCount = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (1 <= grid[i][j]) railCount++;
            }
        }

        boolean[][] visited = new boolean[n][m];
        int y = 0, x = 0, dir = 1;
        visited[y][x] = true;

        while (y != n - 1 && x != m - 1) {
            int ny = y + dy[dir];
            int nx = x + dx[dir];

            if (!inMap(ny, nx)) return false;
            if (grid[ny][nx] == 0 || grid[ny][nx] == -1) return false;
            if (grid[ny][nx] != 3 && visited[ny][nx]) return false;

            int nextRail = rails[grid[ny][nx]];

            boolean canGo = (nextRail & (1 << oppositeDirection(dir))) > 0;
            if (!canGo) return false;

            // 방향 전환
            if (grid[ny][nx] >= 4) {
                for (int d = 0; d < 4; ++d) {
                    if (d == dir) continue;
                    if ((nextRail & (1 << d)) > 0) {
                        dir = d;
                        break;
                    }
                }
            }

            y = ny;
            x = nx;
            railCount--;
        }

        return railCount == 0;
    }

    boolean canPut(int y, int x, int rail, boolean alreadyPut) {
        int[] dirArr = alreadyPut ? new int[]{1, 2} : new int[]{0, 1, 2, 3};
        for (int dir : dirArr) {
            boolean canGo = (rail & (1 << dir)) > 0;

            int ny = y + dy[dir];
            int nx = x + dx[dir];

            if ((dir == 1 || dir == 2) && inMap(ny, nx) && grid[ny][nx] == 0) continue;

            if (isNotCompatible(canGo, ny, nx, dir)) return false;
        }

        return true;
    }

    private boolean isNotCompatible(boolean go, int ny, int nx, int dir) {
        if (go) {
            if (!inMap(ny, nx) || grid[ny][nx] == -1) return true;
            int nextRail = rails[grid[ny][nx]];
            return (nextRail & (1 << oppositeDirection(dir))) == 0;
        } else if (inMap(ny, nx)){
            int railIdx = grid[ny][nx];
            if (railIdx != -1) {
                int nextRail = rails[railIdx];
                return (nextRail & (1 << oppositeDirection(dir))) > 0;
            }
        }
        return false;
    }


    boolean inMap(int i, int j) {
        return 0 <= i && i < n && 0 <= j && j < m;
    }

    int oppositeDirection(int dir) {
        return (dir + 2) % 4;
    }
}