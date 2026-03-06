package baekjoon.clazz.class4.g4_9663;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BitMask {

    static int n, cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        dfs(0, 0, 0);
        System.out.println(cnt);
    }

    static void dfs(int cols, int diags1, int diags2) {
        // 다 골랐을 때 ++cnt
        if (cols == (1 << n) - 1) {
            ++cnt;
            return;
        }

        int available = ~(cols | diags1 | diags2) & (1 << n) - 1;
        while (available > 0) {
            int pos = available & -available;
            available -= pos;
            dfs(
                cols | pos,
                (diags1 | pos) << 1,
                (diags2 | pos) >> 1
            );
        }

    }
}
