import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Try to test the array deque.
 *
 * @source StudentArrayDequeLauncher
 */

public class TestArrayDequeGold {
    @Test
    public void testDeque() {
        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads1 = new ArrayDequeSolution<>();
        ArrayDequeSolution<String> errorSeq = new ArrayDequeSolution<>();

        Integer result = 0, expected = 0;
        while (result.equals(expected)) {
            double numberBetweenZeroAndOne = StdRandom.uniform();
            Integer randNumber = StdRandom.uniform(100);

            if (numberBetweenZeroAndOne < 0.25) {
                sad1.addLast(randNumber);
                ads1.addLast(randNumber);
                errorSeq.addLast("addLast(" + randNumber + ")");
            } else if (numberBetweenZeroAndOne > 0.25 && numberBetweenZeroAndOne < 0.5) {
                sad1.addFirst(randNumber);
                ads1.addFirst(randNumber);
                errorSeq.addLast("addFirst(" + randNumber + ")");
            } else if (numberBetweenZeroAndOne > 0.5 && numberBetweenZeroAndOne < 0.75) {
                if (!sad1.isEmpty() && !ads1.isEmpty()) {
                    result = sad1.removeLast();
                    expected = ads1.removeLast();
                    errorSeq.addLast("removeLast()");
                }
            } else {
                if (!sad1.isEmpty() && !ads1.isEmpty()) {
                    result = sad1.removeFirst();
                    expected = ads1.removeFirst();
                    errorSeq.addLast("removeFirst()");
                }
            }
        }

        // construct the error message
        StringBuilder errMessage = new StringBuilder(errorSeq.removeFirst() + '\n');
        while (!errorSeq.isEmpty()) {
            errMessage.append(errorSeq.removeFirst()).append('\n');
        }
        assertEquals(errMessage.toString(), result, expected);
    }
}
