package baekjoon.clazz.class6.g2_13334;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Line {
        int left, right;

        Line(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    static int n, d;
    static List<Line> lines = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            if (l > r) {
                int t = l;
                l = r;
                r = t;
            }

            lines.add(new Line(l, r));
        }

        d = Integer.parseInt(br.readLine());

        // lines 중에서 d보다 큰 거 필터링 및 right 기준 정렬
        ArrayList<Line> filtered = new ArrayList<>();
        for (Line line : lines) {
            if (line.right - line.left <= d) filtered.add(line);
        }
        filtered.sort(Comparator.comparingInt(l -> l.right));

        int cnt = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (Line line : filtered) {
            int trainRight = line.right;
            pq.offer(line.left);

            while (!pq.isEmpty() && pq.peek() < trainRight - d) {
                pq.poll();
            }

            cnt = Math.max(cnt, pq.size());
        }

        System.out.println(cnt);
    }
}
