import java.util.*;
import java.util.stream.*;

class HeapTopK {
    // get K biggest or smallest elements of the array (or K-th biggest)
    // O(n log n) is general case compelxity
    private static int[] heapTopK(int[] arr, int k, boolean isMax) {
        List<Integer> boxed = IntStream.of(arr).boxed().toList();
        var heap = new PriorityQueue<Integer>((x, y) -> isMax? Integer.compare(y, x): Integer.compare(x,y));
        heap.addAll(boxed);
        ArrayList<Integer> ab = new ArrayList<Integer>();
        for (int i=0; i<k; i++) {
            ab.add(heap.poll());
        }
        return ab.stream().mapToInt(i -> i).toArray();
    }

    // javac HeapTopK.java && java HeapTopK 
    public static void main(String[] args) {
        int[] array = new int[]{1,7,2,6,3,5,4};
        System.out.printf("3 highest numbers of array are %s [7,6,5]\n", Arrays.toString(heapTopK(array, 3, true)));
        System.out.printf("4 lowest numbers of array are %s [1,2,3,4]\n",  Arrays.toString(heapTopK(array, 4, false)));
    }
}