package baekjoon.clazz.class2.s5_1676;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        int cnt = 0;
        int t = 5;
        while (n / t > 0) {
            cnt += (n / t);
            t *= 5;
        }

        System.out.println(cnt);
    }
}
