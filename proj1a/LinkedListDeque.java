/* *****************************************************************************
 *  Name: C.M.
 *  Date: July 31, 2021
 *  Description: generic deque that supports adding and removing items from
 * either the front or the back of the data structure.
 *  Implementation: use the linked list
 **************************************************************************** */

import java.util.NoSuchElementException;

public class LinkedListDeque<Item> {
    final private Node<Item> sentinel;
    private int n;

    /**
     * construct an empty deque
     */
    public LinkedListDeque() {
        sentinel = new Node<Item>();
        sentinel.prior = sentinel;
        sentinel.next = sentinel;
        n = 0;
    }

    private static class Node<Item> {
        private Item item;
        private Node<Item> prior;
        private Node<Item> next;
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
        if (item == null) throw new IllegalArgumentException();
        Node<Item> oldFirst = sentinel.next;
        Node<Item> first = new Node<Item>();
        first.item = item;

        // build four connections
        first.next = oldFirst;
        first.prior = sentinel;
        sentinel.next = first;
        oldFirst.prior = first;
        n++;
    }

    /**
     * Adds an item of type Item to the back of the deque.
     */
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();
        Node<Item> oldLast = sentinel.prior;
        Node<Item> last = new Node<Item>();
        last.item = item;

        // build four connections
        sentinel.prior = last;
        last.next = sentinel;
        last.prior = oldLast;
        oldLast.next = last;
        n++;
    }

    /**
     * Removes and returns the item at the front of the deque. If no such item
     * exists, returns null.
     */
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();

        // find the item of 1st node
        Item temp = sentinel.next.item;

        // find the 2nd node
        Node<Item> first = sentinel.next.next;

        // connect the sentinel and the 2nd item
        sentinel.next = first;
        first.prior = sentinel;
        n--;
        return temp;
    }

    /**
     * Removes and returns the item at the back of the deque. If no such item
     * exists, returns null.
     */
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException();
        // find the item of last node
        Item temp = sentinel.prior.item;

        // find the 2nd last node
        Node<Item> last = sentinel.prior.prior;

        // connect the sentinel and the 2nd last node
        sentinel.prior = last;
        last.next = sentinel;
        n--;
        return temp;
    }

    /**
     * Prints the items in the deque from first to last, separated by a space.
     */
    public void printDeque() {
        Node<Item> tempNode = sentinel.next;
        for (int i = 0; i < n; i++) {
            System.out.print(tempNode.item + ",");
            tempNode = tempNode.next;
        }
        System.out.println();
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next
     * item, and so forth. If no such item exists, returns null. Must not alter
     * the deque!
     */
    public Item get(int index) {
        if (index >= n || index < 0)
            return null;

        Node<Item> tempNode = sentinel.next;
        for (int i = 0; i < index; i++)
            tempNode = tempNode.next;

        return tempNode.item;
    }

    /**
     * Gets the item at the given index recursively.
     */
    public Item getRecursive(int index) {
        return getRecursive(index, sentinel.next);
    }

    /**
     * Helper method to recursively get the item
     */
    private Item getRecursive(int index, Node<Item> node) {
        if (index >= n || index < 0)
            return null;

        if (index == 0)
            return node.item;
        return getRecursive(index - 1, node.next);
    }

    public static void main(String[] args) {
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
        lld1.addFirst(4);
        lld1.addFirst(2);
        lld1.addFirst(1);
        lld1.addFirst(1000);

        lld1.printDeque();
        System.out.println(lld1.get(-9));
        System.out.println(lld1.getRecursive(-9));
        lld1.printDeque();
    }
}
