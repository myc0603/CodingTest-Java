package baekjoon.clazz.class2.b1_14626;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String isbn = br.readLine();

        int rem = 0;
        int idx = -1;
        for (int i = 0; i < 13; ++i) {
            char c = isbn.charAt(i);
            if (c == '*') {
                idx = i;
                continue;
            }

            int num = c - '0';
            if (i % 2 == 0) rem = (rem + num) % 10;
            else rem = (rem + 3 * num) % 10;
        }

        if (idx % 2 == 0) System.out.println(10 - rem);
        else {
//            System.out.println(new int[]{0, 3, 6, 9, 2, 5, 8, 1, 4, 7}[rem]);
            for (int x = 0; x <= 9; ++x) {
                if ((rem + 3 * x) % 10 == 0) {
                    System.out.println(x);
                    return;
                }
            }
        }
    }
}
