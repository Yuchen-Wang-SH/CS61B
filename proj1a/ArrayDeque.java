public class ArrayDeque<T> {
    private int size;
    private int nextFirst;
    private int nextLast;
    private T[] items;
    private double ratio = 0.25;

    public ArrayDeque() {
        size = 0;
        items = (T[]) new Object[8];
        nextFirst = 4;
        nextLast = 5;
    }

    private int decrease(int index) {
        int result = index - 1;
        if (result < 0) {
            result = items.length - 1;
        }
        return result;
    }

    private int increase(int index) {
        int result = index + 1;
        if (result >= items.length) {
            result = 0;
        }
        return result;
    }

    private void resize(int newSize) {
        T[] result = (T[]) new Object[newSize];
        int pOld = increase(nextFirst);
        for (int i = 0; i < size; i++) {
            result[i] = items[pOld];
            pOld = increase(pOld);
        }
        nextFirst = result.length - 1;
        nextLast = items.length;
        items = result;
    }

    public void addFirst(T item) {
        if (size == items.length)   {
            resize(2 * size);
        }
        items[nextFirst] = item;
        nextFirst = decrease(nextFirst);
        size++;
    }

    public void addLast(T item) {
        if (size == items.length)   {
            resize(2 * size);
        }
        items[nextLast] = item;
        nextLast = increase(nextLast);
        size++;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int p = increase(nextFirst);
        for (int i = 0; i < size; i++) {
            System.out.print(items[p]);
            System.out.print(' ');
            p = increase(p);
        }
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        size--;
        nextFirst = increase(nextFirst);
        T result =  items[nextFirst];
        if (size < ratio * items.length && items.length > 8) {
            resize(items.length / 2);
        }
        return result;
    }

    public T removeLast() {
        if (size == 0)  {
            return null;
        }
        size--;
        nextLast = decrease(nextLast);
        T result = items[nextLast];
        if (size < ratio * items.length && items.length > 8) {
            resize(items.length / 2);
        }
        return result;
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        }
        int origin = increase(nextFirst);
        int finalIndex = origin + index;
        if (finalIndex >= items.length) {
            finalIndex -= items.length;
        }
        return items[finalIndex];
    }

//    public static void main(String[] args) {
//        ArrayDeque<Integer> l = new ArrayDeque<>();
//        for (int i = 0; i < 50; i++) {
//            l.addFirst(i);
//        }
//        for (int i = 0; i < 52; i++) {
//            System.out.print(l.removeFirst());
//            System.out.print(' ');
//        }
//        for (int i = 0; i < 50; i++) {
//            l.addFirst(i);
//        }
//        l.printDeque();
//    }
}
