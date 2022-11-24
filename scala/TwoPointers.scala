import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.HashSet

object TwoPointers {
   //Keyword: sorted, pairs / triplets

   // time complexity O(n)
   // space compleixty O(1)
   def findPairsWithTargetSum(arr: Array[Int], target: Int, leftPointer:Int = 0) : Array[Array[Int]] = {
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

   def uniqueTripletsWhichSumIsZero(arr: Array[Int]):Array[Array[Int]] = {
     val res = HashSet[Array[Int]]()
     for (i<-0 until arr.size-1) {
       
        val pairs = findPairsWithTargetSum(arr, -arr(i), i+1)
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
       
        println(s"pairs with target sum 9 are ${findPairsWithTargetSum(array, 9).map(el=>el.mkString("+")).mkString(",")} [1+8,2+7,3+6,4+5]")
        println(s"pairs with target sum 5 are ${findPairsWithTargetSum(array, 9).map(el=>el.mkString("+")).mkString(",")} [1+4,2+3]")

        val array2 = Array(-3,-2,-1,0,1,1,2)
        println(s"unique triplets which are zero in total are ${uniqueTripletsWhichSumIsZero(array2).map(el=>el.mkString("+")).mkString(",")} [-3+1+2,-1+0+1,-2+1+1,-2+0+2]")

        println(s"min length of subarray to be sorted to make the whole array sorted is ${minSubarrayWhichMakesTheArraySortedWhenSorted(Array(1,3,2,0,-1,7,10))} [5] ")
        println(s"min length of subarray to be sorted to make the whole array sorted is ${minSubarrayWhichMakesTheArraySortedWhenSorted(Array(1,2,3))} [0] ")
        println(s"min length of subarray to be sorted to make the whole array sorted is ${minSubarrayWhichMakesTheArraySortedWhenSorted(Array(12,7,8,1,2,0,10,11))} [8] ")

        println(s"array has cycles = ${findCyclesSlowAndFastPointers(Array(1,2,3,4,2))} [true]")
        println(s"array has cycles = ${findCyclesSlowAndFastPointers(Array(1,2,3,4,5))} [false]")
    }

}
