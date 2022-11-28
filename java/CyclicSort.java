import java.util.Arrays;

class CyclicSort {
    // keyword: range from n to m, sort, scrambled

    // sort array of scrambled 1..n in-place  
    // time complexity is 0(n)
    private static int[] sortArray1toN(int[] arr) {
        var i = 0;
        while (i<arr.length) {
            var curr = arr[i];
            if (curr == i+1) {
                i++;
            } else {
                var stash = arr[curr-1];
                arr[curr-1] = curr;
                arr[i] = stash;
            }
        }
    
        return arr;
    }

    private static int findMissedIndex(int[] arr) {
        var i = 0;
        while (i<arr.length) {
            var curr = arr[i];
            if (curr == i || curr>=arr.length) {
                i ++;
            } else {
                var stash = arr[curr];
                arr[curr] = curr;
                arr[i] = stash;
            }
        }
    
        var missedIndex = arr.length;
        for (i=0; i< arr.length; i++) {
            if (arr[i]!=i) {
                missedIndex = i; 
                break;
            }
        }
        
        return missedIndex;
    }
  

    // javac CyclicSort.java && java CyclicSort 
    public static void main(String[] args) {
        int[] array = new int[]{2,6,4,3,1,5};
        System.out.printf("sorted array is %s [1..6]\n", Arrays.toString(sortArray1toN(array)));


        int[] array2 = new int[]{4,0,3,1};
        System.out.printf("missed index is %d [2]\n", findMissedIndex(array2));

        int[] array3 = new int[]{4,0,3,1,2};
        System.out.printf("missed index is %d [5]\n", findMissedIndex(array3));
    }
}