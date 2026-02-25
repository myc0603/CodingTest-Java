package baekjoon.week.week1.n1629_s1;

public class Base2 {
    public static void main(String[] args) {
        int N = 129487;

        StringBuilder sb = new StringBuilder();
        while (N > 0) {
            if (N % 2 == 1) sb.append(1);
            else sb.append(0);
            N /= 2;
        }
        sb.reverse();

        System.out.println(sb);
    }
}
