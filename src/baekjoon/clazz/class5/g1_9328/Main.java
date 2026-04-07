package baekjoon.clazz.class5.g1_9328;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int n, m;
    static char[][] map;
    static boolean[][] visited;
    static boolean[] hasKey = new boolean[26];
    static ArrayList<int[]>[] lockedDoors = new ArrayList[26];
    static ArrayDeque<int[]> q = new ArrayDeque<>();

    static int ans = 0;

    static final int[] dy = {1, -1, 0, 0};
    static final int[] dx = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 26; ++i) lockedDoors[i] = new ArrayList<>();

        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            solve();
        }
        System.out.println(sb);
    }

    static void solve() throws IOException {
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char[n][m];
        visited = new boolean[n][m];
        Arrays.fill(hasKey, false);
        for (int i = 0; i < 26; ++i) lockedDoors[i].clear();

        ans = 0;

        for (int i = 0; i < n; ++i) {
            String line = br.readLine();
            for (int j = 0; j < m; ++j) {
                map[i][j] = line.charAt(j);
            }
        }

        String keys = br.readLine();
        if (!keys.equals("0")) {
            for (int i = 0; i < keys.length(); ++i) {
                char key = keys.charAt(i);
                hasKey[key - 'a'] = true;
            }
        }

        q.clear();
        // padding 방식도 좋은 방법
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (i != 0 && i != n - 1 && j != 0 && j != m - 1) continue;
                if (map[i][j] == '*') continue;
                goTo(i, j);
            }
        }

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int y = cur[0], x = cur[1];

            for (int i = 0; i < 4; ++i) {
                int ny = y + dy[i];
                int nx = x + dx[i];

                if (!inMap(ny, nx) || map[ny][nx] == '*' || visited[ny][nx]) continue;
                goTo(ny, nx);
            }
        }

        sb.append(ans).append('\n');
    }

    static boolean isDoorCannotBeOpened(char c) {
        return 'A' <= c && c <= 'Z' && !hasKey[c - 'A'];
    }

    static void pickKey(int y, int x) {
        char c = map[y][x];
        if (c < 'a' || 'z' < c) return;
        hasKey[c - 'a'] = true;
        q.addAll(lockedDoors[c - 'a']);
        lockedDoors[c - 'a'].clear();
    }

    static void goTo(int y, int x) {
        char c = map[y][x];
        visited[y][x] = true;

        if (isDoorCannotBeOpened(c)) {
            lockedDoors[c - 'A'].add(new int[]{y, x});
            return;
        }

        q.add(new int[]{y, x});

        pickKey(y, x);

        if (c == '$') ans++;
    }

    static boolean inMap(int y, int x) {
        return 0 <= y && y < n && 0 <= x && x < m;
    }

    // gpt
    static void goToByGPT(int y, int x) {
        visited[y][x] = true;
        char c = map[y][x];

        if (isLockedDoor(c)) {
            saveLockedDoor(y, x, c);
            return;
        }

        if (isKey(c)) acquireKey(c);
        if (c == '$') ans++;

        q.add(new int[]{y, x});
    }

    static void acquireKey(char c) {
        int idx = c - 'a';
        if (hasKey[idx]) return;

        hasKey[idx] = true;
        q.addAll(lockedDoors[idx]);
        lockedDoors[idx].clear();
    }

    static boolean isKey(char c) {
        return 'a' <= c && c <= 'z';
    }

    static void saveLockedDoor(int y, int x, char c) {
        lockedDoors[c - 'A'].add(new int[]{y, x});
    }

    static boolean isLockedDoor(char c) {
        return isDoorCannotBeOpened(c);
    }
}
