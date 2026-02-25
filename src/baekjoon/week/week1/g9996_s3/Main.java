package baekjoon.week.week1.g9996_s3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String pat = br.readLine();

        int starIndex = pat.indexOf('*');
        String front = pat.substring(0, starIndex);
        String back = pat.substring(starIndex + 1);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; ++i) {
            String s = br.readLine();
            // front 랑 back 이 겹치는 경우가 있음 ab*ba 인데 aba 인 경우 false
            if (s.length() >= front.length() + back.length() && s.startsWith(front) && s.endsWith(back)) {
                sb.append("DA").append('\n');
            } else sb.append("NE").append('\n');
        }

        System.out.println(sb);
    }
}
