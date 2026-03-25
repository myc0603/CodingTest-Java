package baekjoon.clazz.class4.g2_11444;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    static Map<Long, Long> map = new HashMap<>();
    static long MOD = 1_000_000_007L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(br.readLine());

        System.out.println(dfs(n));
    }

    static long dfs(long cur) {
        if (cur == 1) return 1;
        if (cur == 0) return 0;

        Long l = map.get(cur);
        if (l != null) return l;

        long result;
        long f1 = dfs(cur / 2);
        if (cur % 2 == 0) {
            long f2 = dfs(cur / 2 - 1);
            result = f1 * ((f1 + 2 * f2) % MOD) % MOD;
        } else {
            long f2 = dfs(cur / 2 + 1);
            result = (f1 * f1 % MOD + f2 * f2 % MOD) % MOD;
        }
        map.put(cur, result);
        return result;
    }
}
