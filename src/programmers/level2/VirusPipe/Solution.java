package programmers.level2.VirusPipe;
import java.util.*;

class Solution {
    int n, k;
    ArrayList<int[]>[] tree;

    int answer = 1;

    public static void main(String[] args) {
        Solution s = new Solution();

        // 테스트 케이스 1
        int n1 = 10;
        int infection1 = 1;
        int[][] edges1 = {
                {1, 2, 1},
                {1, 3, 1},
                {1, 4, 3},
                {1, 5, 2},
                {5, 6, 1},
                {5, 7, 1},
                {2, 8, 3},
                {2, 9, 2},
                {9, 10, 1}
        };
        int k1 = 2;

        int result1 = s.solution(n1, infection1, edges1, k1);
        System.out.println("result1 = " + result1); // 기대값: 6


        // 테스트 케이스 2
//        int n2 = 7;
//        int infection2 = 6;
//        int[][] edges2 = {
//                {1, 2, 3},
//                {1, 4, 3},
//                {4, 5, 1},
//                {5, 6, 1},
//                {3, 6, 2},
//                {3, 7, 2}
//        };
//        int k2 = 3;
//
//        int result2 = s.solution(n2, infection2, edges2, k2);
//        System.out.println("result2 = " + result2); // 기대값: 7
    }

    public int solution(int n, int infection, int[][] edges, int k) {
        this.n = n;
        this.k = k;

        tree = new ArrayList[n + 1];
        for (int i = 1; i <= n; ++i) tree[i] = new ArrayList<>();

        for (int[] e : edges) {
            int x = e[0], y = e[1], p = e[2];
            tree[x].add(new int[]{y, p});
            tree[y].add(new int[]{x, p});
        }

        ArrayList<Integer> start = new ArrayList<>();
        start.add(infection);

        dfs(0, start);

        return answer;
    }

    void dfs(int idx, ArrayList<Integer> infected) {
//        System.out.println("idx = " + idx + ", infected = " + infected);

        if (idx == k) {
            answer = Math.max(answer, infected.size());
            return;
        }

        for (int p = 1; p <= 3; ++p) {
            ArrayList<Integer> nextInfected = bfs(infected, p);
            dfs(idx + 1, nextInfected);
        }
    }

    ArrayList<Integer> bfs(ArrayList<Integer> start, int pipe) {
        ArrayList<Integer> ret = new ArrayList<>();

        ArrayDeque<Integer> q = new ArrayDeque<Integer>();
        boolean[] visited = new boolean[n + 1];
        for (Integer x : start) {
            q.add(x);
            visited[x] = true;
        }

        while (!q.isEmpty()) {
            int cur = q.poll();
            ret.add(cur);

            for (int[] next : tree[cur]) {
                if (next[1] != pipe) continue;
                if (visited[next[0]]) continue;

                visited[next[0]] = true;
                q.add(next[0]);
            }
        }

        return ret;
    }
}
