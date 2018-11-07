import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();
    static OffByOne offByOne = new OffByOne();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        assertTrue(palindrome.isPalindrome("racecar"));
        assertTrue(palindrome.isPalindrome("noon"));
        assertTrue(palindrome.isPalindrome(""));
        assertTrue(palindrome.isPalindrome("a"));
        assertFalse(palindrome.isPalindrome("aA"));
        assertFalse(palindrome.isPalindrome("abc"));
    }

    @Test
    public void testIsPalindrome2() {
        assertTrue(palindrome.isPalindrome("flake", offByOne));
        assertTrue(palindrome.isPalindrome(" ", offByOne));
        assertTrue(palindrome.isPalindrome("a", offByOne));
        assertFalse(palindrome.isPalindrome("abba", offByOne));
    }
}
