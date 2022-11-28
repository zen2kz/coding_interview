class Recursion {
    // Tail recursion - if you have a recursive function that calls itself as its last action, then you can reuse the stack frame of that function.
    // By applying that trick, a tail recursive function can execute in constant stack space, 
    // so it's really just another formulation of an iterative process. 
    // I.e. a tail recursive function is the functional form of a loop, and it executes just as efficiently as a loop.

    private static int factIter(int x, int res) {
        return x==0? res : factIter(x-1, res*x);
    }

    // calculates factorial using tail recursion
    private static int factorialTailrec(int n) {
        return factIter(n,1);
    } 

    // calculates greatest common divisor using tail recursion
    private static int gcd(int a, int b) {
        return (b==0)?a:gcd(b, a%b);
    }


    // javac Recursion.java && java Recursion 
    public static void main(String[] args) {
        System.out.printf("factorial of 4 using tail recursion %d [24]\n", factorialTailrec(4));
        System.out.printf("greatest common divisor of 14 and 21 is %d [7]\n", gcd(14,21));
    }
}
