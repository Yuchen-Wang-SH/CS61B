import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    @Test
    public void Testwhatever() {
        StudentArrayDeque<Integer> student = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> solution = new ArrayDequeSolution<>();

        int currentSize = 0;
        int counter = 0;
        String message = "";
        while (true) {
            double number = StdRandom.uniform();
            if (number < 0.25) {
                student.addFirst(counter);
                solution.addFirst(counter);
                currentSize++;
                message += "addFirst(" + counter + ")\n";
            } else if (number < 0.5) {
                student.addLast(counter);
                solution.addLast(counter);
                currentSize++;
                message += "addLast(" + counter + ")\n";
            } else if (number < 0.75) {
                if (currentSize != 0) {
                    message += "removeFirst()\n";
                    assertEquals(message, solution.removeFirst(), student.removeFirst());
                    currentSize--;
                }
            } else if (number <= 1) {
                if (currentSize != 0) {
                    message += "removeLast()\n";
                    assertEquals(message, solution.removeLast(), student.removeLast());
                    currentSize--;
                }
            }
            counter++;
        }
    }
}
