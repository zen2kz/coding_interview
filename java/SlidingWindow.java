import java.util.Arrays;

class SlidingWindow {
    //Problem: When to buy or sell ; max sum of subarray; smallest subarray with a sum of K
    //Keyword: contiguous (i.e. touching each other), longest substring


    // returns k contiguous elements with the highest sum (fixed sliding window)
    // Time complexity O(n)
    // Space complexity O(1)
    private static int[] maxSumOfKContiguousElems(int[] arr, int k) {
        var maxSum = 0;
        var sum = 0;
        var windowStart = 0;
        var size = 0;
        int[] res = null;
        for (int windowEnd = 0; windowEnd< arr.length; windowEnd++) {
            sum += arr[windowEnd];
            size ++;
            if (size == k) {
                if (sum>maxSum) {
                    maxSum = sum;
                    res = Arrays.copyOfRange(arr, windowStart, windowEnd+1);
                } 
                sum -=arr[windowStart];
                size --;
                windowStart = windowStart+1;
            }
        }
        return res;
    }

    private static int[] minNumberOfElementsSum(int[] arr, int target) {
        var sum = 0;
        var minSize = Integer.MAX_VALUE;;
        var windowStart = 0;
        int[] res = null;
        for (int windowEnd = 0; windowEnd< arr.length; windowEnd++) {
            sum += arr[windowEnd];

            while (sum >= target) {
                if (sum == target) {
                    var size = windowEnd-windowStart+1;
                    if (size<minSize) {
                        minSize = size;
                        res = Arrays.copyOfRange(arr,windowStart, windowEnd+1);
                    }
                } 
                sum -=arr[windowStart];
                windowStart++;
            }
        }
        return res;
    }

    // javac SlidingWindow.java && java SlidingWindow 
    public static void main(String[] args) {
        int[] array = new int[]{1,7,2,6,3,5,4,8};
        System.out.printf("3 contiguous elements with max sum are %s [5,4,8]\n", Arrays.toString(maxSumOfKContiguousElems(array, 3)));
        System.out.printf("4 contiguous elements with max sum are %s [5,4,8]\n", Arrays.toString(maxSumOfKContiguousElems(array, 4)));

        System.out.printf("min lenght of elements which gives the total of 8 is %s [8]\n",Arrays.toString(minNumberOfElementsSum(array, 8)));
        System.out.printf("min lenght of elements which gives the total of 11 is %s [2,6,3]\n", Arrays.toString(minNumberOfElementsSum(array, 11)));
    }
}


/*
 * 
 * 
    // returns the shortest sequence of elements which gives the target number
    // Time complexity O(n)
    // Space complexity O(1)
    def minNumberOfElementsSum(arr: Array[Int], target: Int): Array[Int] = {
        var sum = 0
        var windowStart:Int = 0
        var minSize = Int.MaxValue
        var res: Array[Int] = null
        for (windowEnd<-0 until arr.size) {
            sum += arr(windowEnd)

            while (sum >= target) {
                if (sum == target) {
                    val size = windowEnd-windowStart+1
                    if (size<minSize) {
                        minSize = size
                        res = arr.slice(windowStart, windowEnd+1)
                    }
                } 
                sum -=arr(windowStart)
                windowStart = windowStart+1
            }
             
        }
        res
    }

    //scalac SlidingWindow.scala && scala SlidingWindow
    def main(args: Array[String])={
        val array = Array(1,7,2,6,3,5,4,8)
       
        println(s"3 contiguous elements with max sum are ${maxSumOfKContiguousElems(array, 3).mkString(",")} [5,4,8]")
        println(s"4 contiguous elements with max sum are ${maxSumOfKContiguousElems(array, 4).mkString(",")} [3,5,4,8]")

        println(s"min lenght of elements which gives the total of 8 is ${minNumberOfElementsSum(array, 8).mkString(",")} [8]")
        println(s"min lenght of elements which gives the total of 11 is ${minNumberOfElementsSum(array, 11).mkString(",")} [2,6,3]")
    }
 */