package baekjoon.week.week1.k1213_s3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Standard {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        int[] cnt = new int[26];

        for (char c : s.toCharArray()) {
            cnt[c - 'A']++;
        }

        int odd = 0;
        char mid = 0;
        // 홀수개 글자들 확인
        for (int i = 0; i < 26; ++i) {
            if (cnt[i] % 2 == 1) {
                ++odd;
                mid = (char) (i + 'A');
            }
        }

        // 여기까지 내 풀이랑 동일
        // 길이가 짝수일 때 odd == 0 이어야하는 확인은 하지 않음
        // 어차피 짝수고 odd <= 1 이면 자연스레 odd == 0
        if (odd > 1) {
            System.out.println("I'm Sorry Hansoo");
            return;
        }

        // char 배열을 빌드
        char[] result = new char[s.length()];
        int left = 0;
        int right = s.length() - 1;

        // mid 빼고 빌드
        for (int i = 0; i < 26; ++i) {
            while (cnt[i] >= 2) {
                result[left++] = (char) (i + 'A');
                result[right--] = (char) (i + 'A');
                cnt[i] -= 2;
            }
        }

        if (odd == 1) {
            result[left] = mid;
        }

        System.out.println(String.valueOf(result));
    }
}
