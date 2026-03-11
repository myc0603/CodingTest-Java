package baekjoon.clazz.class2.b1_28702;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s1 = br.readLine();
        String s2 = br.readLine();
        String s3 = br.readLine();

        if (isNumber(s1)) {
            int i = Integer.parseInt(s1);
            System.out.println(fizzbuzz(i + 3));
        } else if (isNumber(s2)) {
            int i = Integer.parseInt(s2);
            System.out.println(fizzbuzz(i + 2));
        } else if (isNumber(s3)) {
            int i = Integer.parseInt(s3);
            System.out.println(fizzbuzz(i + 1));
        } else {
            // 세 개가 모두 숫자가 아닌 경우는 없음
        }
    }

    static boolean isNumber(String s) {
        char c = s.charAt(0);
        return '0' <= c && c <= '9';
    }

    static String fizzbuzz(int i) {
        if (i % 3 == 0 && i % 5 == 0) return "FizzBuzz";
        if (i % 3 == 0) return "Fizz";
        if (i % 5 == 0) return "Buzz";
        return String.valueOf(i);
    }
}
