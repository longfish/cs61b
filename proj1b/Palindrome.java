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

    public boolean isPalindrome(String word) {
        Deque<Character> wordQueue = wordToDeque(word);
        while (wordQueue.size() > 1) {
            Character a = wordQueue.removeFirst();
            Character b = wordQueue.removeLast();
            if (!a.equals(b)) {
                return false;
            }
        }
        return true;
    }
}
