package baekjoon.week.week1.n1629_s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Loop {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long a = Integer.parseInt(st.nextToken());
        long b = Integer.parseInt(st.nextToken());
        long c = Integer.parseInt(st.nextToken());

        long rem = 1;
        long temp = a % c;
        while (b > 0) {
            if (b % 2 == 1) {
                rem = rem * temp % c;
            }
            temp = temp * temp % c;
            b /= 2;
        }

        System.out.println(rem);
    }
}
