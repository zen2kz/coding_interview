import scala.annotation.tailrec
object Recursion {
    // Tail recursion - if you have a recursive function that calls itself as its last action, then you can reuse the stack frame of that function.
    // By applying that trick, a tail recursive function can execute in constant stack space, 
    // so it's really just another formulation of an iterative process. 
    // I.e. a tail recursive function is the functional form of a loop, and it executes just as efficiently as a loop.

  
    def factorialTailrec(n:Int) : Int = {
        @tailrec
        def factIter(x: Int, res: Int):Int = {
            if (x==0) res
            else factIter(x-1, res*x)
        }

        factIter(n, 1)
    }

    def gcd(a:Int, b:Int) : Int = {
        @tailrec
        def gcdIter(a:Int, b:Int) : Int = if (b == 0) a else gcdIter(b, a % b)
        
        gcdIter(a,b)
    }


    //scalac Recursion.scala && scala Recursion
    def main(args: Array[String])={
        println(s"factorial of 4 using tail recursion ${factorialTailrec(4)} [24]")
        println(s"greatest common divisor of 14 and 21 is ${gcd(14,21)} [7]")
    }
}
