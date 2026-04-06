package baekjoon.clazz.class5.g3_9466;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            solve();
        }
        System.out.println(sb);
    }

    static void solve() throws IOException {
        int n = Integer.parseInt(br.readLine());

        int ans = n;

        boolean[] isCycle = new boolean[n + 1];
        int[] choices = new int[n + 1];
        int[] visited = new int[n + 1];
        Arrays.fill(visited, -1);

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; ++i) {
            choices[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= n; ++i) {
            if (visited[i] != -1) continue;

            int cur = i;
            int idx = 0;
            ArrayList<Integer> onPath = new ArrayList<>();

            while (true) {
                visited[cur] = idx;
                onPath.add(cur);

                int next = choices[cur];
                int nextIdx = visited[next];
                if (nextIdx == -1) {
                    ++idx;
                    cur = next;
                    continue;
                }

                // cycle 만난 경우
                if (isCycle[next]) break;

                // cycle 은 아니지만 이미 체크한 경우 (cycle과 연결된 노드)
                // -> 이 로직은 next가 cycle 인지와 무관함 -> isCycle 불필요
                if (nextIdx >= onPath.size() || onPath.get(nextIdx) != next) break;

                // find cycle
                for (int j = nextIdx; j < onPath.size(); ++j) {
                    isCycle[onPath.get(j)] = true;
                }
                ans -= onPath.size() - nextIdx;

                break;
            }
        }

        sb.append(ans).append('\n');
    }
}
