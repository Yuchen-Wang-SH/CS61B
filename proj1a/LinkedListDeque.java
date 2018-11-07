//public class LinkedListDeque<T> {
//    /* two sentinel topology */
//    private class ItemNode {
//        public T item;
//        public ItemNode next;
//        public ItemNode previous;
//
//        public ItemNode (T item, ItemNode previous, ItemNode next) {
//            this.item = item;
//            this.previous = previous;
//            this.next = next;
//        }
//    }
//    private int size;
//    private ItemNode sentFront, sentBack;
//
//    public LinkedListDeque() {
//        size = 0;
//        sentFront = new ItemNode(null, null, null);
//        sentBack = new ItemNode(null, null, null);
//        sentFront.next = sentBack;
//        sentBack.previous = sentFront;
//    }
//
//    public void addFirst(T item) {
//        ItemNode node = new ItemNode(item, sentFront, sentFront.next);
//        sentFront.next.previous = node;
//        sentFront.next = node;
//        size += 1;
//    }
//
//    public void addLast(T item) {
//        ItemNode node = new ItemNode(item, sentBack.previous, sentBack);
//        sentBack.previous.next = node;
//        sentBack.previous = node;
//        size += 1;
//    }
//
//    public boolean isEmpty() {
//        return (size == 0);
//    }
//
//    public int size() {
//        return size;
//    }
//
//    public void printDeque() {
//        ItemNode p = sentFront.next;
//        while (p != sentBack) {
//            System.out.print(p.item);
//            System.out.print(' ');
//            p = p.next;
//        }
//    }
//
//    public T removeFirst() {
//        if (isEmpty()) return null;
//        size --;
//        ItemNode p = sentFront.next;
//        p.next.previous = sentFront;
//        sentFront.next = p.next;
//        T t = p.item;
//        p = null;
//        return t;
//    }
//
//    public T removeLast() {
//        if (isEmpty()) return null;
//        size --;
//        ItemNode p = sentBack.previous;
//        p.previous.next = sentBack;
//        sentBack.previous = p.previous;
//        T t = p.item;
//        p = null;
//        return t;
//    }
//
//    public T get(int index) {
//        if (index >= size) return null;
//        // Count from sentFront
//        if (index < size / 2) {
//            ItemNode p = sentFront;
//            for (int i = 0; i != index; i++) p = p.next;
//            return p.item;
//        }
//        // Count from sentBack
//        else {
//            ItemNode p = sentBack;
//            for (int i = size; i != index; i-- ) p = p.previous;
//            return p.item;
//        }
//    }
//
//    private T getRecursive(ItemNode sentinel, int index) {
//        if (index == 0) return sentinel.next.item;
//        else return getRecursive(sentinel.next, index-1);
//    }
//
//    public T getRecursive(int index) {
//        if (index >= size) return null;
//        return getRecursive(sentFront, index);
//    }
//
//    public static void main(String[] args) {
//        LinkedListDeque<Integer> l = new LinkedListDeque<>();
//        l.addFirst(1);
//        l.addLast(2);
//        l.addLast(3);
//        l.addFirst(0);
//        l.printDeque();
//        l.removeFirst();
//        l.removeLast();
//        System.out.println(l.get(1));
//        System.out.println(l.getRecursive(1));
//    }
//}

public class LinkedListDeque<T> {
    /* Circular topology */
    private class ItemNode {
        private T item;
        private ItemNode next;
        private ItemNode previous;

        public ItemNode(T item, ItemNode previous, ItemNode next) {
            this.item = item;
            this.previous = previous;
            this.next = next;
        }
    }
    private int size;
    private ItemNode sentinel;

    public LinkedListDeque() {
        size = 0;
        sentinel = new ItemNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.previous = sentinel;
    }

    public void addFirst(T item) {
        ItemNode node = new ItemNode(item, sentinel, sentinel.next);
        sentinel.next.previous = node;
        sentinel.next = node;
        size++;
    }

    public void addLast(T item) {
        ItemNode node = new ItemNode(item, sentinel.previous, sentinel);
        sentinel.previous.next = node;
        sentinel.previous = node;
        size++;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        ItemNode p = sentinel.next;
        while (p != sentinel) {
            System.out.print(p.item);
            p = p.next;
        }
    }

    public T removeFirst() {
        // Do not have to check whether the list is empty or not
        ItemNode node = sentinel.next;
        node.next.previous = node.previous;
        node.previous.next = node.next;
        T t = node.item;
        node = null;
        // Or removing from an empty list will cause negative size.
        if (size != 0) {
            size--;
        }
        return t;
    }

    public T removeLast() {
        // Do not have to check wheter the list is empty or not
        ItemNode node = sentinel.previous;
        node.previous.next = node.next;
        node.next.previous = node.previous;
        T t = node.item;
        node = null;
        if (size != 0) {
            size--;
        }
        return t;
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        }
        ItemNode p = sentinel.next;
        while (index > 0) {
            p = p.next;
            index--;
        }
        return p.item;
    }

    private T getRecursive(ItemNode sent, int index) {
        if (index == 0) {
            return sent.next.item;
        } else {
            return getRecursive(sent.next, index - 1);
        }
    }

    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        }
        return getRecursive(sentinel, index);
    }

//    public static void main(String[] args) {
//        LinkedListDeque<Integer> l = new LinkedListDeque<>();
//        l.addFirst(1);
//        l.addLast(2);
//        l.addLast(3);
//        l.addFirst(0);
//        l.printDeque();
//        l.removeFirst();
//        l.removeLast();
//        System.out.println(l.get(1));
//        System.out.println(l.getRecursive(1));
//    }
}
