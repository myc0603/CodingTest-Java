package baekjoon.category.impl.g4_2580;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Bitmask {
    static int[][] board = new int[9][9];

    static int[] rows = new int[9];
    static int[] cols = new int[9];
    static int[] squares = new int[9];

    static ArrayList<int[]> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 0; i < 9; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; ++j) {
                int x = Integer.parseInt(st.nextToken());

                board[i][j] = x;

                if (x == 0) {
                    list.add(new int[]{i, j});
                    continue;
                }

                rows[i] |= 1 << x;
                cols[j] |= 1 << x;
                squares[squareIdx(i, j)] |= 1 << x;
            }
        }

        dfs(0);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                sb.append(board[i][j]).append(' ');
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }

    static boolean dfs(int cur) {
        if (cur == list.size()) return true;

        int i = list.get(cur)[0], j = list.get(cur)[1];
        for (int x = 1; x <= 9; ++x) {
            if (((rows[i] | cols[j] | squares[squareIdx(i, j)]) & (1 << x)) > 0) continue;

            board[i][j] = x;
            rows[i] |= 1 << x;
            cols[j] |= 1 << x;
            squares[squareIdx(i, j)] |= 1 << x;

            if (dfs(cur + 1)) return true;

            board[i][j] = 0;
            rows[i] &= ~(1 << x);
            cols[j] &= ~(1 << x);
            squares[squareIdx(i, j)] &= ~(1 << x);

//            rows[i] ^= 1 << x;
//            cols[j] ^= 1 << x;
//            squares[squareIdx(i, j)] ^= 1 << x;
        }

        return false;
    }

    static int squareIdx(int i, int j) {
        return (i / 3) * 3 + (j / 3);
    }
}
