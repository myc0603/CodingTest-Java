package baekjoon.week.week1.n1629_s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Recursion {

    static long power(long a, long b, long c) {
        if (b == 1) return a % c;
        long powered = power(a, b / 2, c);
        powered = powered * powered % c;
        if (b % 2 == 1) powered = powered * a % c;
        return powered;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long a = Integer.parseInt(st.nextToken());
        long b = Integer.parseInt(st.nextToken());
        long c = Integer.parseInt(st.nextToken());

        System.out.println(power(a, b, c));
    }
}
