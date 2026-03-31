package baekjoon.clazz.class6.g3_14725;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// NullPointerException 발생
// NPE 해결 이후 96%에서 틀림
public class Main2 {
    static String DELIMITER = "--";
    static String[][] arr;

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        arr = new String[n][16];

        StringTokenizer st;
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());

            for (int j = 0; j < k; ++j) {
                arr[i][j] = st.nextToken();
            }
        }

        Arrays.sort(arr, (a1, a2) -> {
            int minLen = Math.min(a1.length, a2.length);
            for (int i = 0; i < minLen; ++i) {
                // null 처리
                if (a1[i] == null || a2[i] == null) break;
                int compared = comparingString(a1[i], a2[i]);
                if (compared == 0) continue;
                return compared;
            }

            return 0;
        });

        print(0, 0);
        for (int i = 1; i < n; ++i) {
            String[] prev = arr[i - 1];
            String[] cur = arr[i];
            int j = 0;

            while (prev[j] != null && prev[j].equals(cur[j])) ++j;

            print(i, j);
        }
        System.out.println(sb);
    }

    static int comparingString(String s1, String s2) {
        int minLen = Math.min(s1.length(), s2.length());
        for (int i = 0; i < minLen; ++i) {
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);
            if (c1 == c2) continue;
            return Character.compare(c1, c2);
        }

        return 0;
    }

    static void print(int i, int start) {
        for (int depth = start; depth < 16 && arr[i][depth] != null; ++depth) {
            for (int j = 0; j < depth; ++j) sb.append(DELIMITER);
            sb.append(arr[i][depth]).append('\n');
        }
    }
}
