object BinarySearch {
    // Iterative is less time/space consuming than recuirsive
    // Time complexity O(log n)
    // Applies to sorted array
    def binarySearchIterative(arr:Array[Int], target: Int): Int = {
        var low = 0
        var high = arr.size - 1

        while (low <= high) {
            var mid = low + (high - low) / 2

            if (arr(mid) == target) {
                return mid
            } else if (arr(mid)> target) {
                high = mid - 1
            } else {
                low = mid + 1
            }
        }
        
        -1
    }

    // scalac BinarySearch.scala && scala BinarySearch
    def main(args: Array[String])={
        val array = Array(1,2,3,4,5,6,7)
        println(s"Index of 4 is ${binarySearchIterative(array, 4)} [3]")
        println(s"Index of 1 is ${binarySearchIterative(array, 1)} [0]")
        println(s"Index of 7 is ${binarySearchIterative(array, 7)} [6]")
    }
}