package baekjoon.week.week2.l2852_s3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] score = new int[3];
        int[] times = new int[3];
        int prevTime = 0;

        StringTokenizer st;
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            int team = Integer.parseInt(st.nextToken());
            int time = toSec(st.nextToken());

            // times 계산
            // 이기고 있는 팀
            int winning = score[1] > score[2] ? 1 : (score[1] < score[2] ? 2 : 0);
            times[winning] += time - prevTime;

            // score 갱신
            ++score[team];

            prevTime = time;
        }
        int winning = score[1] > score[2] ? 1 : (score[1] < score[2] ? 2 : 0);
        times[winning] += toSec("48:00") - prevTime;

        System.out.println(toTime(times[1]));
        System.out.println(toTime(times[2]));
    }

    static int toSec(String time) {
        String[] split = time.split(":");
        return Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
    }

    static String toTime(int sec) {
        int min = sec / 60;
        sec = sec % 60;
        String minStr = min < 10 ? "0" + min : String.valueOf(min);
        String secStr = sec < 10 ? "0" + sec : String.valueOf(sec);
        return minStr + ":" + secStr;
    }
}
