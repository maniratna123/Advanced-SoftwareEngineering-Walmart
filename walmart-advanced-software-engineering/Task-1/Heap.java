import java.util.ArrayList;

public class Heap<T extends Comparable<T>> {
    private final int childCount;
    private final ArrayList<T> data;

    // Constructor that accepts the number of children (must be a power of 2)
    public Heap(int childCount) {
        this.validateChildCount(childCount);
        this.childCount = childCount;
        this.data = new ArrayList<T>();
    }

    // Validate that the number of children is a power of 2 and greater than zero
    private void validateChildCount(int childCount) {
        if (childCount <= 0) {
            throw new IllegalArgumentException("childCount must be greater than zero");
        }

        double logChildCount = Math.log(childCount) / Math.log(2);
        if (Math.ceil(logChildCount) != Math.floor(logChildCount)) {
            throw new IllegalArgumentException("childCount must be a power of 2");
        }
    }

    // Insert an item into the heap and adjust positions to maintain the heap property
    public void insert(T item) {
        data.add(item);
        int itemIndex = data.size() - 1;
        while (itemIndex > 0) {
            itemIndex = this.swapUp(itemIndex);
        }
    }

    // Helper method to swap the child with its parent if needed (bubble up)
    private int swapUp(int childIndex) {
        T childValue = data.get(childIndex);
        int parentIndex = (int) Math.floor((float) (childIndex - 1) / childCount);
        
        if (parentIndex >= 0) {
            T parentValue = data.get(parentIndex);
            if (childValue.compareTo(parentValue) > 0) {
                data.set(parentIndex, childValue);
                data.set(childIndex, parentValue);
                return parentIndex;
            }
        }
        return -1;
    }

    // Pop the max (root) value off the heap and adjust the heap structure accordingly
    public T popMax() {
        if (data.size() > 0) {
            T maxItem = data.get(0);
            if (data.size() > 1) {
                T lastItem = data.remove(data.size() - 1);
                data.set(0, lastItem);
                int itemIndex = 0;
                while (itemIndex >= 0) {
                    itemIndex = this.swapDown(itemIndex);
                }
            }
            return maxItem;
        } else {
            return null; // Return null if the heap is empty
        }
    }

    // Helper method to swap the parent with its largest child (bubble down)
    private int swapDown(int parentIndex) {
        T parentValue = data.get(parentIndex);
        int largestChildIndex = -1;
        T largestChildValue = null;

        // Check all the children of the current node
        for (int i = 0; i < childCount; i++) {
            int childIndex = childCount * parentIndex + i + 1;

            if (childIndex < data.size()) {
                T childValue = data.get(childIndex);

                if (largestChildValue == null || childValue.compareTo(largestChildValue) > 0) {
                    largestChildIndex = childIndex;
                    largestChildValue = childValue;
                }
            }
        }

        // Perform the swap if necessary
        if (largestChildValue != null && parentValue.compareTo(largestChildValue) < 0) {
            data.set(parentIndex, largestChildValue);
            data.set(largestChildIndex, parentValue);
            return largestChildIndex;
        }

        return -1; // No more swaps needed
    }
}
