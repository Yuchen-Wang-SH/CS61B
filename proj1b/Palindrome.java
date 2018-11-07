public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        LinkedListDeque<Character> result = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            result.addLast(word.charAt(i));
        }
        return result;
    }

    private boolean isPalindrome(Deque deque) {
        if (deque.isEmpty() || deque.size() == 1) {
            return true;
        }
        if (deque.removeFirst() == deque.removeLast()) {
            return isPalindrome(deque);
        } else {
            return false;
        }
    }

    public boolean isPalindrome(String word) {
        Deque<Character> deque = wordToDeque(word);
        return isPalindrome(deque);
    }

    private boolean isPalindrome(Deque deque, CharacterComparator cc) {
        if (deque.isEmpty() || deque.size() == 1) {
            return true;
        }
        if (cc.equalChars((char)deque.removeFirst(), (char)deque.removeLast())) {
            return isPalindrome(deque, cc);
        } else {
            return false;
        }
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> deque = wordToDeque(word);
        return isPalindrome(deque, cc);
    }

    public static void main(String[] args) {
        OffByOne offByOne = new OffByOne();
        Palindrome palindrome = new Palindrome();
        palindrome.isPalindrome("flake", offByOne);
    }
}
