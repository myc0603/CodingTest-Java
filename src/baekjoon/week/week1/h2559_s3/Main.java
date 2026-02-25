package baekjoon.week.week1.h2559_s3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] t = new int[n];
        int[] psum = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            t[i] = Integer.parseInt(st.nextToken());
            psum[i + 1] = t[i] + psum[i];
        }

        // psum[k]( - psum[0])도 포함해야 함
        // 아니면 max 를 psum[k] 로 초기화 하던가
        int max = -100 * 100000;
        for (int i = k; i <= n; ++i) {
            max = Math.max(max, psum[i] - psum[i - k]);
        }
        System.out.println(max);
    }
}
