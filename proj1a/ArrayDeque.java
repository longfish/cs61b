/* *****************************************************************************
 *  Name: C.M.
 *  Date: Aug 1, 2021
 *  Description: generic deque that supports adding and removing items from
 * either the front or the back of the data structure.
 *  Implementation: use the array
 **************************************************************************** */

public class ArrayDeque<Item> {

    private static final int INIT_CAPACITY = 8;
    // initial capacity of underlying resizing array

    private Item[] deque; // holds the items
    private int n; // number of items on queue
    private int first; // index of first element of deque
    private int last; // index of next available slot

    /**
     * Construct an empty deque.
     */
    public ArrayDeque() {
        deque = (Item[]) new Object[INIT_CAPACITY];
        n = 0;
        first = 0;
        last = 1;
    }

    /**
     * Returns true if deque is empty, false otherwise.
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * Returns the number of items in the deque.
     */
    public int size() {
        return n;
    }

    /**
     * Adds an item of type Item to the front of the deque.
     */
    public void addFirst(Item item) {
        if (item == null) {
            return;
        }

        if (n == deque.length) {
            expand(2 * deque.length);
        }

        deque[first] = item;
        first -= 1;
        if (first == -1) {
            first = deque.length - 1; // wrap-around
        }
        ++n;
    }

    /**
     * Adds an item of type Item to the back of the deque.
     */
    public void addLast(Item item) {
        if (item == null) {
            return;
        }

        if (n == deque.length) {
            expand(2 * deque.length);
        }

        deque[last] = item;
        last += 1;
        if (last == deque.length) {
            last = 0; //wrap-around
        }
        ++n;
    }

    /**
     * Removes and returns the item at the front of the deque. If no such item
     * exists, returns null.
     */
    public Item removeFirst() {
        if (isEmpty()) {
            return null;
        }

        first++;
        if (first == deque.length) {
            first = 0;
        }

        Item item = deque[first];
        deque[first] = null; // avoid loitering
        n--;
        if (n > 3 && n == deque.length / 4) {
            shrink(deque.length / 2);
        }
        return item;
    }

    /**
     * Removes and returns the item at the back of the deque. If no such item
     * exists, returns null.
     */
    public Item removeLast() {
        if (isEmpty()) {
            return null;
        }

        last--;
        if (last == -1) {
            last = deque.length - 1;
        }
        Item item = deque[last];
        deque[last] = null; // avoid loitering
        n--;
        if (n > 3 && n == deque.length / 4) {
            shrink(deque.length / 2);
        }
        return item;
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next
     * item, and so forth. If no such item exists, returns null. Must not alter
     * the deque!
     */
    public Item get(int index) {
        if (index < 0 || index >= n) {
            return null;
        }
        return deque[(first + index + 1) % deque.length];
    }

    /**
     * Prints the items in the deque from first to last, separated by a space.
     */
    public void printDeque() {
        for (int i = 0; i < n; i++) {
            System.out.print(deque[(first + i + 1) % deque.length] + " ");
        }
        System.out.println();
    }

    public void expand(int capacity) {
        assert capacity >= n;
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            copy[i] = deque[(first + i + 1) % deque.length];
        }
        deque = copy;
        first = deque.length - 1;
        last = n;
    }

    public void shrink(int capacity) {
        assert capacity >= n;
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            copy[i] = deque[(first + i + 1) % deque.length];
        }
        deque = copy;
        first = deque.length - 1;
        last = n;
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
        lld1.addLast(30);
        lld1.addLast(30);
        lld1.addLast(30);
        lld1.addLast(30);
        lld1.removeFirst();
        lld1.addFirst(4);
        lld1.addFirst(2);
        lld1.addFirst(1);
        lld1.addFirst(1000);
        lld1.addFirst(1000);
        lld1.addFirst(1600);
        lld1.addFirst(120);
        lld1.addFirst(1010);

        lld1.printDeque();
        System.out.println(lld1.get(6));
        lld1.printDeque();
    }
}
