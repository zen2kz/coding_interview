import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.HashSet

object TwoPointers {
   //Keyword: sorted, pairs / triplets

   // time complexity O(n)
   // space compleixty O(1)
   // This is a simple algorithm when there is no duplicates in the array
   def findPairsWithTargetSumNoDuplicates(arr: Array[Int], target: Int, leftPointer:Int = 0) : Array[Array[Int]] = {
      //arr should be sorted 
      var res: ArrayBuffer[Array[Int]] = ArrayBuffer()
      var left:Int = leftPointer
      var right:Int = arr.length-1

      while (left<right) {
        val currSum = arr(left)+arr(right)
        if (currSum == target) {
            res += Array(arr(left),arr(right))
            left +=1
            right -=1
        } else if (currSum<target){
            left +=1
        } else if (currSum > target) {
            right -=1
        }
      }

      res.toArray
   }

   // time complexity O(n log n) - due to sorting
   // space compleixty O(n) - we are creating a copy of the array
   // This version covers edge cases when the array can contain duplicates
   def findNumberOfPairsWithTargetSumWithEdgeCases(srcArr: Array[Int], target: Int, leftPointer:Int = 0) : Int = {
      val arr = srcArr.sorted
      var count = 0
      var left:Int = leftPointer
      var right:Int = arr.length-1

      while (left<right) {
        val lv = arr(left)
        val rv = arr(right)
        val currSum = lv+rv
        if (currSum == target) {
            if (lv!=rv) {
                var countLeft = 0
                while (arr(left) == lv) {
                    countLeft+=1
                    left +=1
                }
                var countRight = 0
                while (arr(right) == rv) {
                    countRight+=1
                    right -=1
                }
                count += countLeft*countRight
            } else {
                val k = right-left
                count += (k*(k+1))/2  
                // 2-> 1, 3->3, 4->6, 5->10, 6->15
                return count
            }
        } else if (currSum<target){
            left +=1
        } else if (currSum > target) {
            right -=1
        }
      }

      count
   }

   def uniqueTripletsWhichSumIsZero(arr: Array[Int]):Array[Array[Int]] = {
     val res = HashSet[Array[Int]]()
     for (i<-0 until arr.size-1) {
       
        val pairs = findPairsWithTargetSumNoDuplicates(arr, -arr(i), i+1)
        pairs.foreach(p => {
            res += arr(i)+:p
        })
     }

     res.toArray
   }

   // Time complexity is O(n)
   def minSubarrayWhichMakesTheArraySortedWhenSorted(arr: Array[Int]):Int = {
      var left:Int = 0
      var right:Int = arr.length-1
      var res:Int = 0

      while (left<right) {
        var leftFound = false
        var rightFound = false
        if (arr(left)<=arr(left+1)) {
            left+=1
        } else {
            leftFound = true
        }
        if (arr(right)>=arr(right-1)) {
            right-=1
        } else {
            rightFound = true
        }

        if (leftFound && rightFound) {
            val subArr = arr.slice(left, right+1)
            val min = subArr.min
            val max = subArr.max

            for (i<-left-1 to 0 by -1) {
                if (arr(i)>min) {
                    left = i
                }
            }

            for (i<-right+1 until arr.size) {
                if (arr(i)<max) {
                    right = i
                }
            }
            
            return right-left+1
        }

      }

      return 0
   }


   // Hare and tortoise algorithm / or floyd's cycle detection
   // can be used for cases like checking if squares of each digit of an integer are eventually equal to 1 (solving by building a single inked list and look for cycles)
   // Time complexity is O(n)
   // Space complexity is O(1)
   def findCyclesSlowAndFastPointers(arr:Array[Int]): Boolean = {
        var slow = 0
        var fast = 1

        while (true) {
            if (fast<0 || fast>= arr.size) {
                return false
            }
            if (slow<0 || slow>= arr.size) {
                return false
            }
            if (slow==fast) {
                return true
            }
            
            slow = arr(slow)
            fast = arr(fast)
            if (fast<0 || fast>= arr.size) {
                return false
            }
            fast = arr(fast)
        }

        false
   }


    //scalac TwoPointers.scala && scala TwoPointers
    def main(args: Array[String])={
        val array = Array(1,2,3,4,5,6,7,8)
       
        println(s"pairs with target sum 9 are ${findPairsWithTargetSumNoDuplicates(array, 9).map(el=>el.mkString("+")).mkString(",")} [1+8,2+7,3+6,4+5]")
        println(s"pairs with target sum 5 are ${findPairsWithTargetSumNoDuplicates(array, 9).map(el=>el.mkString("+")).mkString(",")} [1+4,2+3]")

        println(s"number of pairs with duplicates with target sum 120 of Array(60,60,60) is ${findNumberOfPairsWithTargetSumWithEdgeCases(Array(60,60,60), 120)} [3]")
        println(s"number of pairs with duplicates with target sum 120 of Array(60,60,60,60,60) is ${findNumberOfPairsWithTargetSumWithEdgeCases(Array(60,60,60,60,60), 120)} [10]")
        println(s"number of pairs with duplicates with target sum 11 is ${findNumberOfPairsWithTargetSumWithEdgeCases(Array(1,2,3,4,5,5,5,6,6,7,8), 11)} [8]")

        val array2 = Array(-3,-2,-1,0,1,1,2)
        println(s"unique triplets which are zero in total are ${uniqueTripletsWhichSumIsZero(array2).map(el=>el.mkString("+")).mkString(",")} [-3+1+2,-1+0+1,-2+1+1,-2+0+2]")

        println(s"min length of subarray to be sorted to make the whole array sorted is ${minSubarrayWhichMakesTheArraySortedWhenSorted(Array(1,3,2,0,-1,7,10))} [5] ")
        println(s"min length of subarray to be sorted to make the whole array sorted is ${minSubarrayWhichMakesTheArraySortedWhenSorted(Array(1,2,3))} [0] ")
        println(s"min length of subarray to be sorted to make the whole array sorted is ${minSubarrayWhichMakesTheArraySortedWhenSorted(Array(12,7,8,1,2,0,10,11))} [8] ")

        println(s"array has cycles = ${findCyclesSlowAndFastPointers(Array(1,2,3,4,2))} [true]")
        println(s"array has cycles = ${findCyclesSlowAndFastPointers(Array(1,2,3,4,5))} [false]")
    }

}
