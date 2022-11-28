import scala.util.control.Breaks._

object CyclicSort {
  // keyword: range from n to m, sort, scrambled

  // sort array of scrambled 1..n in-place  
  // time complexity is 0(n)
  def sortArray1toN(arr: Array[Int]): Array[Int] = {
    var i = 0
    while (i<arr.size) {
        var curr = arr(i)
        if (curr == i+1) {
            i +=1
        } else {
            val stash = arr(curr-1)
            arr(curr-1) = curr
            arr(i) = stash
        }
    }

    arr
  }

  def findMissingIndex(arr: Array[Int]): Int = {
    var i = 0
    while (i<arr.size) {
        var curr = arr(i)
        if (curr == i || curr>=arr.size) {
            i +=1
        } else {
            val stash = arr(curr)
            arr(curr) = curr
            arr(i) = stash
        }
    }

    var missedIndex = arr.size
    breakable {
        for (i<-0 until arr.size) {
            if (arr(i)!=i) {
                missedIndex = i 
                break
            }
        }
    }
    missedIndex
  }

  //scalac CyclicSort.scala && scala CyclicSort
  def main(args: Array[String])={
        val array = Array(2,6,4,3,1,5)
        println(s"sorted array is ${sortArray1toN(array).mkString(",")} [1..6]")

        val array2 = Array(4,0,3,1)
        println(s"missed index is ${findMissingIndex(array2)} [2]")

        val array3 = Array(4,0,3,1,2)
        println(s"missed index is ${findMissingIndex(array3)} [5]")
    }
}
