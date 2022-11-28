class BinarySearch {
    // Iterative is less time/space consuming than recuirsive
    // Time complexity O(log n)
    // Applies to sorted array
    private static int binarySearchIterative(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid]> target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        
        return -1;
    }

    // javac BinarySearch.java && java BinarySearch 
    public static void main(String[] args) {
        int[] array = new int[]{1,2,3,4,5,6,7};

        System.out.printf("index of 4 is %d [3]\n", binarySearchIterative(array, 4));
        System.out.printf("index of 1 is %d [0]\n", binarySearchIterative(array, 1));
        System.out.printf("index of 7 is %d [6]\n", binarySearchIterative(array, 7));
    }
}
