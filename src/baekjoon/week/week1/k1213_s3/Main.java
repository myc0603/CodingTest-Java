package baekjoon.week.week1.k1213_s3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        char[] arr = s.toCharArray();

        // s 길이가 짝수
        // 다 짝수개

        // s 길이가 홀수
        // 하나만 홀수개

        // 순서대로 cnt/2개씩 추가, 홀수개면 홀수개인거 추가, 기존꺼 뒤집어서 추가

        int oddCnt = 0;
        int[] charCnt = new int[27];

        for (char c : arr) {
            ++charCnt[c - 'A'];
        }

        StringBuilder sb = new StringBuilder();
        char oddChar = ' ';
        for (int i = 0; i < 26; ++i) {
            if (charCnt[i] % 2 == 1) {
                ++oddCnt;
                oddChar = (char) (i + 'A');
            }
            for (int j = 0; j < charCnt[i] / 2; ++j) sb.append((char) (i + 'A'));
        }
        if ((s.length() % 2 == 1 && oddCnt != 1) ||
            (s.length() % 2 == 0 && oddCnt != 0)
        ) {
            System.out.println("I'm Sorry Hansoo");
            return;
        }

        String front = sb.toString();
        String back = sb.reverse().toString();

        if (s.length() % 2 == 1) {
            System.out.println(front + oddChar + back);
        } else {
            System.out.println(front + back);
        }
    }
}
