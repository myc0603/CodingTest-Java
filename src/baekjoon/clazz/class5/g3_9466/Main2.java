package baekjoon.clazz.class5.g3_9466;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// gpt가 권장한 더 좋은 코드 (기존과 같은 방식)
public class Main2 {
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

        int[] choices = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int node = 1; node <= n; ++node) {
            choices[node] = Integer.parseInt(st.nextToken());
        }

        int[] visitRound = new int[n + 1];
        int[] depth = new int[n + 1];
        int round = 0;
        int ans = n;
        for (int i = 1; i <= n; ++i) {
            if (visitRound[i] != 0) continue;

            round++;
            int cur = i;
            int d = 1;

            while (visitRound[cur] == 0) {
                visitRound[cur] = round;
                depth[cur] = d++;
                cur = choices[cur];
            }

            if (visitRound[cur] == round) {
                ans -= d - depth[cur];
            }
        }

        sb.append(ans).append('\n');
    }
}
