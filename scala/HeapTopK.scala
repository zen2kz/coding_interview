import collection.mutable
//import scala.math.Ordering.Implicits._

object HeapTopK {
   // get K biggest elements of the array (or K-th biggest)
   // O(n log n) is general case compelxity
   def heapMaxTopK(arr: Array[Int], k: Int): Array[Int] = {
        val heap: mutable.PriorityQueue[Int] = mutable.PriorityQueue.from(arr)
         
        val ab = mutable.ArrayBuffer[Int]()
        for (i<-0 until k) {
           ab += heap.dequeue()
        }
        ab.toArray
   }

   object MinOrder extends Ordering[Int] {
        def compare(x:Int, y:Int) = y compare x
    }

    // get K smallest elements of the array (or K-th smallest)
    // O(n log n) is general case compelxity
    def heapMinTopK(arr: Array[Int], k: Int): Array[Int] = {
        val heap: mutable.PriorityQueue[Int] = mutable.PriorityQueue.from(arr)(MinOrder)

        val ab = mutable.ArrayBuffer[Int]()
        for (i<-0 until k) {
            ab += heap.dequeue()
        }
        ab.toArray
   }

    //scalac HeapTopK.scala && scala HeapTopK
    def main(args: Array[String])={
        val array = Array(1,7,2,6,3,5,4)
        println(s"3 highest numbers of array are ${heapMaxTopK(array, 3).mkString(",")} [7,6,5]")
        println(s"4 lowest numbers of array are ${heapMinTopK(array, 4).mkString(",")} [1,2,3,4]")
    }
}
