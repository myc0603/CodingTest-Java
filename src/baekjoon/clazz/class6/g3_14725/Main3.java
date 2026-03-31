package baekjoon.clazz.class6.g3_14725;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// NPE 해결 이후 96%에서 틀림
// Main2에서 배열 쓰던 걸 ArrayList로 바꾼거 밖에 없음
public class Main3 {
    static String DELIMITER = "--";
    static ArrayList<ArrayList<String>> list = new ArrayList<>();

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());

            list.add(new ArrayList<>());
            for (int j = 0; j < k; ++j) {
                list.get(i).add(st.nextToken());
            }
        }

        list.sort((a1, a2) -> {
            int minLen = Math.min(a1.size(), a2.size());
            for (int i = 0; i < minLen; ++i) {
                int compared = comparingString(a1.get(i), a2.get(i));
                if (compared == 0) continue;
                return compared;
            }
            // 이건 상관없긴함
//            return Integer.compare(a1.size(), a2.size());
            return 0;
        });

        print(0, 0);
        for (int i = 1; i < n; ++i) {
            ArrayList<String> prev = list.get(i - 1);
            ArrayList<String> cur = list.get(i);

            int j = 0;
            while (j < prev.size() && j < cur.size() && prev.get(j).equals(cur.get(j))) ++j;

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

//        return 0;
        // 이거 고치니까 맞음
        return Integer.compare(s1.length(), s2.length());
    }

    static void print(int i, int start) {
        for (int depth = start; depth < list.get(i).size(); ++depth) {
            for (int j = 0; j < depth; ++j) sb.append(DELIMITER);
            sb.append(list.get(i).get(depth)).append('\n');
        }
    }
}
