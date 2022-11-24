object QuickSort {
   // built-in function scala.util.Sorting.quickSort(testArray)
   // scala default .sorted algorithm is Dual-Pivot Quick sort O(n log n) for primitives and TimSort for objects


   // Avg complexity O(n log n), worst case O(pow(n,2))
   // Space complexity O(n log n) - as it is in-place sort
   def quickSort(arr: Array[Int], low: Int, high:Int): Unit = {
    if (low<high) {
        val p = partition(arr, low, high)
        quickSort(arr, low, p-1)
        quickSort(arr, p+1, high)
    }
   }

   def swap(arr: Array[Int], pos1:Int, pos2:Int): Unit = {
    val stash = arr(pos1)
    arr(pos1) = arr(pos2)
    arr(pos2) = stash
   }

   def partition(arr: Array[Int], low:Int, high:Int):Int = {
    val pivot  = high
    var i = low

    for (j<-low to high if arr(j)<arr(pivot)) {
        swap(arr,i,j)
        i+=1
    }

    swap(arr, i, pivot)
    i
   }

    //scalac QuickSort.scala && scala QuickSort
    def main(args: Array[String])={
        val array = Array(1,7,2,6,3,5,4)
        quickSort(array,0, array.size-1)
        println(s"Sorted array is ${array.mkString(",")} [1,2,3,4,5,6,7]")
    }
}
