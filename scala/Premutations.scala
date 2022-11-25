import scala.collection.mutable.ListBuffer

object Premutations {
  // time complexity O(pow(n,2)) due to removing element form an array
  // space complexity O(n)
  def findKthPremutation(sn:Int, sk:Int): String = {
    var k:Int = sk
    var n:Int = sn
    var res = ""
    var digits = ListBuffer[Int]()
    for (i<-1 to n) {
        digits +=i
    }

    val fact = Array.ofDim[Int](n+1)
    fact(0) = 1
    for (i<-1 to n) {
        fact(i) = i*fact(i-1) 
    }
    
    // to get 0-based indexes
    k -=1

    while (n>0) {
        val partLenght = fact(n)/n
        val i = k/partLenght
        res += digits(i)
        digits.remove(i)
        n -=1
        k = k%partLenght
    }

    res
  }

   //scalac Premutations.scala && scala Premutations
  def main(args: Array[String])={
        println(s"K-th premutation of combinations of N (n=3, k=4) is ${findKthPremutation(3,4)} [231]")
        println(s"K-th premutation of combinations of N (n=4, k=16) is ${findKthPremutation(4,15)} [3412]")
        println(s"K-th premutation of combinations of N (n=4, k=1) is ${findKthPremutation(4,1)} [1234]")
        println(s"K-th premutation of combinations of N (n=4, k=24) is ${findKthPremutation(4,15)} [4321]")
    }
}
