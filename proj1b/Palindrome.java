/**
 * A class for palindrome operation.
 */
public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        ArrayDeque<Character> wordQueue = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i++) {
            wordQueue.addLast(word.charAt(i));
        }
        return wordQueue;
    }
}
