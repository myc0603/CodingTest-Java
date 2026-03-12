package baekjoon.week.week3.n9934_s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main2 {
    static class Node {
        Node left, right;
        int value;
    }

    static int k;
    static int[] orders;
    static int orderIdx;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());

        int orderSize = (1 << k) - 1;
        orders = new int[orderSize];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < orderSize; ++i) {
            orders[i] = Integer.parseInt(st.nextToken());
        }

        // build tree
        Node tree = buildTree();

        // set order for each node
        dfs(tree);

        // print order
        StringBuilder sb = new StringBuilder();
        ArrayDeque<Node> q = new ArrayDeque<>();
        q.add(tree);

        while (!q.isEmpty()) {

            int qSize = q.size();
            for (int i = 0; i < qSize; ++i) {
                Node cur = q.poll();

                sb.append(cur.value).append(' ');

                if (cur.left != null) q.add(cur.left);
                if (cur.right != null) q.add(cur.right);
            }

            sb.append('\n');
        }

        System.out.println(sb);
    }

    static Node buildTree() {
        Node root = new Node();
        mkChildOf(root, 1);
        return root;
    }

    static void mkChildOf(Node node, int depth) {
        if (depth == k) return;
        node.left = new Node();
        mkChildOf(node.left, depth + 1);

        node.right = new Node();
        mkChildOf(node.right, depth + 1);
    }

    static void dfs(Node node) {
        if (node.left != null) dfs(node.left);
        node.value = orders[orderIdx++];
        if (node.right != null) dfs(node.right);
    }
}
