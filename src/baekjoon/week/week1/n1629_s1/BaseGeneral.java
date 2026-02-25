package baekjoon.week.week1.n1629_s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BaseGeneral {

    public static void main(String[] args) throws IOException {
        int N = 918273;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int base = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (N > 0) {
            int x = N % base;
            sb.append(x);
            N /= base;
        }
        sb.reverse();

        System.out.println(sb);
    }
}
