package baekjoon.clazz.class5.g3_9252;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

// LCS 자체를 구하는 걸 실패...
public class Main {
    static String s1, s2;

    static ArrayList<Integer>[] s1Index = new ArrayList[26];

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 26; ++i) s1Index[i] = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        s1 = br.readLine();
        s2 = br.readLine();

        for (int i = 0; i < s1.length(); ++i) {
            char c = s1.charAt(i);
            s1Index[c - 'A'].add(i);
        }

        ArrayList<Integer>[] lcsLens = new ArrayList[s2.length()];
        for (int i = 0; i < s2.length(); ++i) lcsLens[i] = new ArrayList<>();
        int[] prev = new int[s2.length()];
        Arrays.fill(prev, -1);

        int end = -1;
        int maxLen = 0;
        for (int i = 0; i < s2.length(); ++i) {
            ArrayList<Integer> s1IdxAtI = s1Index[s2.charAt(i) - 'A'];
            for (Integer idxI : s1IdxAtI) {
                int len = 1;

                for (int j = 0; j < i; ++j) {
                    ArrayList<Integer> s1IdxAtJ = s1Index[s2.charAt(j) - 'A'];
                    for (int k = 0; k < s1IdxAtJ.size(); ++k) {
                        Integer idxJ = s1IdxAtJ.get(k);
                        Integer lcsLen = lcsLens[j].get(k);

                        if (idxJ < idxI && len < lcsLen + 1) {
                            len = lcsLen + 1;
                            prev[i] = j;
                        }
                    }
                }

                if (maxLen < len) {
                    maxLen = len;
                    end = i;
                }
                lcsLens[i].add(len);
            }
        }

        StringBuilder sb = new StringBuilder();

        int cur = end;
        while (cur != -1) {
            sb.append(s2.charAt(cur));
            cur = prev[cur];
        }

        sb.append('\n').append(maxLen);

        sb.reverse();

        System.out.println(sb);
    }
}
