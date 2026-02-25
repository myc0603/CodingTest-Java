package baekjoon.week.week1.o4375_s3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int get(int n) {
        int tmp = 1;
        int cnt = 1;
        while (tmp % n != 0) {
            tmp = (tmp * 10 + 1) % n;
            ++cnt;
        }
        return cnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String s;
        while ((s = br.readLine()) != null) {
            int n = Integer.parseInt(s);
            sb.append(get(n)).append('\n');
        }

        System.out.println(sb);
    }
}
