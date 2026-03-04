package baekjoon.clazz.class2.b2_15829;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int l, r = 31, m = 1234567891;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        l = Integer.parseInt(br.readLine());
        System.out.println(hash(br.readLine()));
    }

    static long hash(String s) {
        long result = 0;
        for (int i = 0; i < l; ++i) {
            int c = s.charAt(i) - 'a' + 1;

            result = (result + (c * power(i) % m)) % m;
        }
        return result;
    }

    // r^i
    static long power(long i) {
        long result = 1;

        long tmp = r;
        while (i > 0) {
            if (i % 2 == 1) result = result * tmp % m;
            tmp  = tmp * tmp % m;
            i /= 2;
        }
        return result;
    }
}
