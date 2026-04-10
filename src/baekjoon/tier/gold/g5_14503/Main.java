package baekjoon.tier.gold.g5_14503;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[][] room;

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    // robot
    static int r, c, d;

    // answer
    static int cleanCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        room = new int[n][m];

        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; ++j) {
                room[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (true) {
            if (room[r][c] == 0) {
                room[r][c] = 2;
                ++cleanCnt;
            }

            int y, x;
            if (isAllCleaned()) {
                int backDir = (d + 2) % 4;
                y = r + dy[backDir];
                x = c + dx[backDir];

                if (room[y][x] == 1) break;
            } else {
                do {
                    d = (d + 3) % 4;
                    y = r + dy[d];
                    x = c + dx[d];
                } while (room[y][x] != 0);
            }

            r = y;
            c = x;
        }

        System.out.println(cleanCnt);
    }

    static boolean isAllCleaned() {
        for (int i = 0; i < 4; ++i) {
            int y = r + dy[i];
            int x = c + dx[i];
            if (room[y][x] == 0) return false;
        }
        return true;
    }
}
