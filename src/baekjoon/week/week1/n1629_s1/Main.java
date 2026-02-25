package baekjoon.week.week1.n1629_s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static long power(long a, long b, long c) {
        if (b == 1) return a * b % c;
        long powered = power(a, b / 2, c);
        return ((powered * powered) % c) * ((b % 2 == 1 ? a % c : 1) % c) % c;
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
