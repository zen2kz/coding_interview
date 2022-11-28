import java.util.*;
import java.util.stream.*;

class BinaryTree {
    // DFS traversal
    // - inorder = left-Root-Right
    // - preorder = Root-Left-Right
    // - postorder = Left-Right-Root 

    // DFS vs BFS
    // time complexity is the same O(n)
    // DFS requires extra space due to function call stack (recursive overhead) - O(h) where h is the depth of the graph   
    // If in our task the solution is most likely close to the root - use BFS - if close to leaf - DFS

    // records are available in Java 15+
    record Tree(int value, Tree left, Tree right) {}; 

    // time complexity is O(n) where n is number of nodes
    private static ArrayList<ArrayList<Integer>> breadthFirstLevels(Tree head) {
        Queue<Tree> queue = new ArrayDeque<>();
        queue.add(head);
        ArrayList<ArrayList<Integer>> res =  new ArrayList<>();
        while (!queue.isEmpty()) {
            ArrayList<Integer> level = new ArrayList<>();
            Queue<Tree> nextLevel = new ArrayDeque<>();
            while (!queue.isEmpty()) {
                Tree curr = queue.poll();
                level.add(curr.value);
                if (curr.left!=null) {
                    nextLevel.add(curr.left);
                }
                if (curr.right!=null) {
                    nextLevel.add(curr.right);
                }
            }
            res.add(level);
            queue = nextLevel;
        }
        return res;
    }

    private static Tree buildBinaryTree1ToN(int n) {
        ArrayList<Integer> items = new ArrayList<>();
        for (int i=1;i<=n; i++) {
            items.add(i);
        }

        return buildLevel(items, 0);
    }

    private static void printInOrder(Tree node) {
        if (node!=null) {
            printInOrder(node.left);
            System.out.print(node.value+ " ");
            printInOrder(node.right);
        }
    }

    private static Tree buildLevel(ArrayList<Integer> arr, int i) {
        Tree root = null;
        
        // Base case for recursion
        if (i < arr.size()) {
            Tree left = buildLevel(arr, 2 * i + 1);
            Tree right = buildLevel(arr, 2 * i + 2);
            
            root = new Tree(arr.get(i).intValue(), left, right);
        }
        return root;
    }

    // javac BinaryTree.java && java BinaryTree 
    public static void main(String[] args) {
        Tree tree = buildBinaryTree1ToN(6);
        System.out.printf("binary tree (in order) ");
        printInOrder(tree);
        System.out.println();

        ArrayList<ArrayList<Integer>> levels = breadthFirstLevels(tree);
        String res = Stream.of(levels).map(String::valueOf).collect(Collectors.joining(","));
        System.out.printf("binary tree levels are %s [[1], [2,3], [4,5,6]]\n", res);
    }
}
