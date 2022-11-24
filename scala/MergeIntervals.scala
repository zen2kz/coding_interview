import scala.collection.mutable.ArrayBuffer
import scala.annotation.newMain

object MergeIntervals {

  // Time complexity O(n log n)+O(n) = O(n log n) - due to sorting 
  // Space complexity O(n) as we are creating a new sorted array
  def mergeIntervals(arr: Array[Array[Int]]): Array[Array[Int]] = {
    val sorted = arr.sortBy(_(0))
    val res = ArrayBuffer[Array[Int]]()

    var curr = sorted(0)
    var i = 0
    while (true) {
        i +=1 
        if (i>=sorted.size) {
            res += curr
            return res.toArray
        }
        val next = sorted(i)
        if (next(0)>curr(1)) {
            res += curr
            curr = next
        } else {
            curr(1) = Integer.max(curr(1), next(1))
        }
    }

    res.toArray
  }

  // allows to find free intervals between ranges (for example a time slot for a meeting between N employees)
  // Time complexity O(n log n)+O(n) = O(n log n) - due to sorting 
  // Space complexity O(n) as we are creating a new sorted array
  def freeTime(arr: Array[Array[Array[Int]]], min: Int, max: Int): Array[Array[Int]] = {
    val allRanges = arr.flatten.sortBy(_(0))
    val merged = mergeIntervals(arr.flatten)
    var res = ArrayBuffer[Array[Int]]()
    var start = min
    merged.foreach(el=> {
        if (el(0)>start) {
            res += Array(start, el(0)-1)
        }
        start = el(1)
    })
    if (start<max) {
        res += Array(start, max)
    }
    res.toArray
  }

  //scalac MergeIntervals.scala && scala MergeIntervals
  def main(args: Array[String])={
        val array = Array(Array(1,4),Array(2,5), Array(7,9))
        println(s"merged intervals are ${mergeIntervals(array).map(el=>el.mkString("->")).mkString(",")} [1->5,7->9]")

        val array2 = Array(Array(Array(1,3), Array(5,6)),Array(Array(2,3), Array(6,8)))
        println(s"free time ranges for 2 employees are ${freeTime(array2, 1,8).map(el=>el.mkString("->")).mkString(",")} [3->5]")
    }
}
