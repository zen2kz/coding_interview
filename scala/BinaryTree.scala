import scala.collection.mutable.ArrayBuffer

object BinaryTree {
   // DFS traversal
   // - inorder = left-Root-Right
   // - preorder = Root-Left-Right
   // - postorder = Left-Right-Root 

   // DFS vs BFS
   // time complexity is the same O(n)
   // DFS requires extra space due to function call stack (recursive overhead) - O(h) where h is the depth of the graph   
   // If in our task the solution is most likely close to the root - use BFS - if close to leaf - DFS

  case class Tree(value:Integer, left:Option[Tree], right:Option[Tree])

  // time complexity is O(n) where n is number of nodes
  def breadthFirstLevels(head: Tree): Array[Array[Int]] = {
    var queue = scala.collection.mutable.Queue[Tree](head)
    val res = ArrayBuffer[Array[Int]]()
    while (!queue.isEmpty) {
        var level = Array[Int]()
        val nextLevel = scala.collection.mutable.Queue[Tree]()
        while (!queue.isEmpty) {
            val curr = queue.dequeue()
            level = level :+ curr.value
            if (curr.left.isDefined) {
                nextLevel.enqueue(curr.left.get)
            }
            if (curr.right.isDefined) {
                nextLevel.enqueue(curr.right.get)
            }
        }
        res += level
        queue = nextLevel
    } 
    res.toArray
  }

  def buildBinaryTree1ToN(n: Int): Tree = {
      var items = ArrayBuffer[Int]()
      for (i<-1 to n) {
        items += i
      }
      buildLevel(items.toArray,0).get
  }

  def printInOrder(node: Option[Tree]):Unit = {
    if (node.isDefined) {
        val currNode = node.get
        printInOrder(currNode.left)
        print(s"${currNode.value} ")
        printInOrder(currNode.right)
    }
  }

  def buildLevel(arr: Array[Int],  i:Int):Option[Tree] = {
        var root:Option[Tree] = None
        
        // Base case for recursion
        if (i < arr.length) {
            val left = buildLevel(arr, 2 * i + 1);
            val right = buildLevel(arr, 2 * i + 2);
            
            root = Some(Tree(arr(i), left, right))
        }
        root
    }

  //scalac BinaryTree.scala && scala BinaryTree
  def main(args: Array[String])={
        //val tree = Tree(1, Some(Tree(2, Some(Tree(4, None, None)), Some(Tree(5, None, None)))),Some(Tree(3, Some(Tree(6, None, None)), None)))

        val tree = buildBinaryTree1ToN(6)
        println(s"binary tree (in order) ${printInOrder(Some(tree))}")

        println(s"binary tree levels are ${breadthFirstLevels(tree).map(_.mkString(","))mkString("|")} [1|2,3|4,5,6]")
    }
}
