public class HeapTest {
  public static void main(String[] args) {
      // Create a heap with 4 children per node (which is 2^2, a valid power of 2)
      Heap<Integer> heap = new Heap<>(4);

      // Insert values into the heap
      heap.insert(10);
      heap.insert(20);
      heap.insert(5);
      heap.insert(30);
      heap.insert(25);
      heap.insert(15);

      // Test popMax method (this should return the maximum element, in this case 30)
      System.out.println("Max element popped: " + heap.popMax()); // Should print 30

      // Insert a few more values
      heap.insert(40);
      heap.insert(35);

      // Test popMax again
      System.out.println("Max element popped: " + heap.popMax()); // Should print 40
      System.out.println("Max element popped: " + heap.popMax()); // Should print 35

      // Continue popping elements to test the behavior
      System.out.println("Max element popped: " + heap.popMax()); // Should print 25
      System.out.println("Max element popped: " + heap.popMax()); // Should print 20
      System.out.println("Max element popped: " + heap.popMax()); // Should print 15
      System.out.println("Max element popped: " + heap.popMax()); // Should print 10
      System.out.println("Max element popped: " + heap.popMax()); // Should print 5

      // Test when the heap is empty
      System.out.println("Max element popped: " + heap.popMax()); // Should print null
  }
}
