package baekjoon.category.impl.g4_14499;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int y, x;

    // 위, 오, 뒤, 밑, 왼, 앞
    static int[] dice = new int[6];
    static int[][] map;

    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        y = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        int k = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; ++j) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; ++i) {
            int dir = Integer.parseInt(st.nextToken()) - 1;
            if (roll(dir)) sb.append(dice[0]).append('\n');
        }

        System.out.println(sb);
    }

    static boolean roll(int dir) {
        int ny = y + dy[dir];
        int nx = x + dx[dir];
        if (!inMap(ny, nx)) return false;

        y = ny;
        x = nx;

        int a = dir / 2 + 1;

        int[] idx = {0, a, 3, a + 3};
        if (dir % 2 == 1) reverse(idx);

        int tmp = dice[idx[3]];
        dice[idx[3]] = dice[idx[2]];
        dice[idx[2]] = dice[idx[1]];
        dice[idx[1]] = dice[idx[0]];
        dice[idx[0]] = tmp;

        int downIdx = 3;
        if (map[y][x] == 0) {
            map[y][x] = dice[downIdx];
        } else {
            dice[downIdx] = map[y][x];
            map[y][x] = 0;
        }

        return true;
    }

    static boolean inMap(int y, int x) {
        return 0 <= y && y < n && 0 <= x && x < m;
    }

    static void reverse(int[] arr) {
        for (int i = 0; i < arr.length / 2; ++i) {
            int tmp = arr[i];
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = tmp;
        }
    }
}
