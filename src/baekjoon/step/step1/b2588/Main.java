package baekjoon.step.step1.b2588;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int a = Integer.parseInt(br.readLine());
        int b = Integer.parseInt(br.readLine());

        int b1 = b % 10;
        int b2 = b % 100 - b1;
        int b3 = b - b2 - b1;

        int one = b1 * a;
        int ten = b2 * a / 10;
        int hund = b3 * a / 100;

        bw.write(String.valueOf(one));
        bw.newLine();
        bw.write(String.valueOf(ten));
        bw.newLine();
        bw.write(String.valueOf(hund));
        bw.newLine();

        bw.write(String.valueOf(one + ten * 10 + hund * 100));
        bw.flush();
    }
}
