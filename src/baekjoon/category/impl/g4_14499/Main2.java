package baekjoon.category.impl.g4_14499;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2 {
    static int n, m;
    static int y, x;
    static int[][] map;
    static int u, d, f, b, l, r;

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
            if (roll(dir)) sb.append(u).append('\n');
        }

        System.out.println(sb);
    }

    static boolean roll(int dir) {
        int ny = y + dy[dir];
        int nx = x + dx[dir];

        if (!inMap(ny, nx)) return false;

        y = ny;
        x = nx;

        if (dir == 0) east();
        else if (dir == 1) west();
        else if (dir == 2) north();
        else if (dir == 3) south();

        if (map[y][x] == 0) {
            map[y][x] = d;
        } else {
            d = map[y][x];
            map[y][x] = 0;
        }

        return true;
    }

    static void east() {
        int tmp = u;
        u = l;
        l = d;
        d = r;
        r = tmp;
    }

    static void west() {
        int tmp = u;
        u = r;
        r = d;
        d = l;
        l = tmp;
    }

    static void north() {
        int tmp = u;
        u = f;
        f = d;
        d = b;
        b = tmp;
    }

    static void south() {
        int tmp = u;
        u = b;
        b = d;
        d = f;
        f = tmp;
    }

    static boolean inMap(int y, int x) {
        return 0 <= y && y < n && 0 <= x && x < m;
    }

}
