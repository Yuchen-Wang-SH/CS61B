package hw4.puzzle;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class SearchNode implements Comparable<SearchNode> {
    private WorldState w;
    private int numMove;
    private SearchNode prev;

    public SearchNode(WorldState w, int numMove, SearchNode prev) {
        this.w = w;
        this.numMove = numMove;
        this.prev = prev;
    }

    public WorldState getW() {
        return w;
    }

    public int getNumMove() {
        return numMove;
    }

    public SearchNode getPrev() {
        return prev;
    }

    public static Iterable<WorldState> getSolution(SearchNode s) {
        Stack<WorldState> stack = new Stack<>();
        int iter = s.numMove + 1;
        for (int i = 0; i < iter; i++) {
            stack.push(s.getW());
            s = s.prev;
        }
        List<WorldState> l = new ArrayList<>();
        while (!stack.empty()) {
            l.add(stack.pop());
        }
        return l;
    }

    @Override
    public int compareTo(SearchNode o) {
        int distThis = numMove + w.estimatedDistanceToGoal();
        int distThat = o.numMove + o.w.estimatedDistanceToGoal();
        if (distThis < distThat) {
            return -1;
        } else if (distThis > distThat) {
            return 1;
        }
        return 0;
    }
}
