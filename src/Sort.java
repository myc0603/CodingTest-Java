import java.util.ArrayList;

public class Sort {

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(56);
        list.add(7);

        System.out.println(list);

        list.sort((a, b) -> a - b);
        System.out.println(list);
        list.sort((a, b) -> b - a);
        System.out.println(list);
    }
}
