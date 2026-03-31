package baekjoon.clazz.class6.g3_14725;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main4 {
    static class Node {
        String name;
        TreeMap<String, Node> children = new TreeMap<>();

        Node(String name) {
            this.name = name;
        }
    }

    static Node root = new Node("root");
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());

            int k = Integer.parseInt(st.nextToken());
            Node parent = root;
            for (int j = 0; j < k; ++j) {
                String name = st.nextToken();
                Node child;
                if (!parent.children.containsKey(name)) {
                     child = new Node(name);
                    parent.children.put(name, child);
                } else {
                    child = parent.children.get(name);
                }

                parent = child;
            }
        }

        for (Node child : root.children.values()) {
            dfs(child, 0);
        }

        System.out.println(sb);
    }

    static void dfs(Node cur, int depth) {
        for (int i = 0; i < depth; ++i) {
            sb.append("--");
        }
        sb.append(cur.name).append('\n');

        for (Node child : cur.children.values()) {
            dfs(child, depth + 1);
        }
    }
}
