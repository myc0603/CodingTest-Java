package practice.pq;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;

class MinHeap {
    ArrayList<Integer> heap = new ArrayList<>();

    public void offer(int x) {
        heap.add(x);

        int idx = heap.size() - 1;
        int p = (idx - 1) / 2;
        while (idx > 0 && heap.get(idx) < heap.get(p)) {
            swap(idx, p);
            idx = p;
            p = (idx - 1) / 2;
        }
    }

    public int poll() {
        if (heap.isEmpty()) throw new RuntimeException("heap is empty");
        int ret = heap.getFirst();
        int last = heap.removeLast();

        if (!heap.isEmpty()){
            heap.set(0, last);

            int idx = 0;
            int c1 = 1, c2 = 2;
            int minIdx = getMinIdx(c1, c2);
            while (minIdx != -1 && heap.get(idx) > heap.get(minIdx)) {
                swap(idx, minIdx);

                idx = minIdx;
                c1 = 2 * idx + 1;
                c2 = 2 * idx + 2;
                minIdx = getMinIdx(c1, c2);
            }
        }

        return ret;
    }

    private int getMinIdx(int c1, int c2) {
        if (c2 < heap.size()) return heap.get(c1) < heap.get(c2) ? c1 : c2;
        if (c1 < heap.size()) return c1;
        return -1;
    }

    public int peek() {
        if (heap.isEmpty()) throw new RuntimeException("heap is empty");
        return heap.getFirst();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    private void swap(int i, int j) {
        int t = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, t);
    }

    public void printHeap() {
        System.out.println(heap);
    }
}

public class Main {
    public static void main(String[] args) {
        testBasic();
        testSortedOutput();
        testRandom();
        testEdgeCase();
    }

    // 1. 기본 동작 테스트
    static void testBasic() {
        System.out.println("=== testBasic ===");

        MinHeap h = new MinHeap();
        h.offer(5);
        h.offer(3);
        h.offer(8);
        h.offer(1);

        h.printHeap(); // 내부 상태 확인

        assertEqual(h.peek(), 1);

        assertEqual(h.poll(), 1);
        assertEqual(h.poll(), 3);
        assertEqual(h.poll(), 5);
        assertEqual(h.poll(), 8);

        System.out.println("PASS\n");
    }

    // 2. 전체 정렬 확인
    static void testSortedOutput() {
        System.out.println("=== testSortedOutput ===");

        MinHeap h = new MinHeap();
        int[] arr = {7, 2, 9, 1, 5, 3};

        for (int x : arr) h.offer(x);

        int prev = Integer.MIN_VALUE;

        while (!h.isEmpty()) {
            int cur = h.poll();
            if (cur < prev) {
                throw new RuntimeException("정렬 깨짐!");
            }
            prev = cur;
            System.out.print(cur + " ");
        }

        System.out.println("\nPASS\n");
    }

    // 3. 랜덤 테스트 (핵심)
    static void testRandom() {
        System.out.println("=== testRandom ===");

        MinHeap h = new MinHeap();
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        Random rand = new Random();

        for (int i = 0; i < 1000; i++) {
            int x = rand.nextInt(10000);
            h.offer(x);
            pq.offer(x);
        }

        while (!pq.isEmpty()) {
            int a = h.poll();
            int b = pq.poll();

            if (a != b) {
                throw new RuntimeException("Mismatch! " + a + " vs " + b);
            }
        }

        System.out.println("PASS\n");
    }

    // 4. 예외 케이스
    static void testEdgeCase() {
        System.out.println("=== testEdgeCase ===");

        MinHeap h = new MinHeap();

        try {
            h.poll();
            throw new RuntimeException("예외 안터짐");
        } catch (RuntimeException e) {
            System.out.println("빈 힙 poll 예외 정상");
        }

        h.offer(10);
        assertEqual(h.poll(), 10);

        if (!h.isEmpty()) {
            throw new RuntimeException("비어있어야 함");
        }

        System.out.println("PASS\n");
    }

    // 간단 assert
    static void assertEqual(int a, int b) {
        if (a != b) {
            throw new RuntimeException("Assertion failed: " + a + " != " + b);
        }
    }

}
