public class SLList {
    private class IntNode {
        public int item;
        public IntNode next;
        public IntNode(int item, IntNode next) {
            this.item = item;
            this.next = next;
        }
    }

    private IntNode first;

    private void print() {
        IntNode p = first;
        do {
            System.out.print(p.item);
            System.out.print(' ');
            p = p.next;
        } while (p != null);
    }

    private IntNode getLast() {
        IntNode p = first;
        while (p.next != null)  p = p.next;
        return p;
    }

    public void addFirst(int x) {
        first = new IntNode(x, first);
    }

    public void insert(int item, int position) {
        if (first == null || position == 0) addFirst(item);
        else {
            IntNode p = first;
            // Move to position - 1
            for (int i = 0; i < position - 1; i++)  {
                if (p.next == null) break;
                p = p.next;
            }
            IntNode node = new IntNode(item, p.next);
            p.next = node;
        }
    }

    private IntNode reverseRecursiveHelper(IntNode firstNode) {
        // Return the first node of the reversed linked list started with first
        if (firstNode == null || firstNode.next == null) return firstNode;
        IntNode restList = reverseRecursiveHelper(firstNode.next);
        firstNode.next.next = firstNode;
        firstNode.next = null;
        return restList;
    }

    public void reverse() {
        // Iterative: my solution
        
        // IntNode p1 = null;
        // IntNode p2 = first;
        // while (p2 != null) {
        //     IntNode p2Next = p2.next;
        //     p2.next = p1;
        //     p1 = p2;
        //     p2 = p2Next;
        // }
        // first = p1;

        // Iterative: staff 's solution
        // IntNode firstNode = null;
        // IntNode nextAdd = first;
        // while (nextAdd != null) {
        //     IntNode nodesLeft = nextAdd.next;
        //     nextAdd.next = firstNode;
        //     firstNode = nextAdd;
        //     nextAdd = nodesLeft;
        // }
        // first = firstNode;
        first = reverseRecursiveHelper(first);
    }

    public static void main(String args[]){
        SLList l = new SLList();
        l.addFirst(1);
        l.insert(0, 0);
        l.insert(2, 2);
        l.insert(3, 100);
        l.insert(4, 2);
        l.reverse();
        l.print();
    }

}