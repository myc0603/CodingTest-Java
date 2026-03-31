package baekjoon.clazz.class6.g3_14725;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    // 리프 노드 수
    static int n;
    static String DELIMITER = "--";
    static Map<String, Set<String>>[] tree = new Map[16];
    static Set<String> root = new TreeSet<>();

    static StringBuilder sb = new StringBuilder();

    // 중위순회
    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 16; ++i) tree[i] = new HashMap<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());

            String parent = st.nextToken();
            root.add(parent);

            for (int j = 0; j < k - 1; ++j) {
                String name = st.nextToken();

                if (!tree[j].containsKey(parent)) {
                    tree[j].put(parent, new TreeSet<>());
                }
                tree[j].get(parent).add(name);

                parent = name;
            }
        }

        for (String first : root) {
            dfs(first, 0);
        }
        System.out.println(sb);
    }

    static void dfs(String name, int depth) {
        for (int i = 0; i < depth; ++i) {
            sb.append(DELIMITER);
        }
        sb.append(name).append('\n');

        Set<String> children = tree[depth].get(name);
        if (children == null) return;

        for (String next : children) {
            dfs(next, depth + 1);
        }
    }
}
