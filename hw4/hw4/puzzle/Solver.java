package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;

public class Solver {
    private int numMoves;
    private Iterable<WorldState> solution;

    public Solver(WorldState initial) {
        MinPQ<SearchNode> pq = new MinPQ<>();
        pq.insert(new SearchNode(initial, 0, null));

        while (!pq.isEmpty()) {
            SearchNode s = pq.delMin();
            WorldState w = s.getW();
            if (w.isGoal()) {
                numMoves = s.getNumMove();
                solution = SearchNode.getSolution(s);
                return;
            } else {
                for (WorldState n: w.neighbors()) {
                    // Do not have grandparent or have grandparent but not equal
                    if (s.getPrev() == null) {
                        pq.insert(new SearchNode(n, s.getNumMove() + 1, s));
                    } else if (!n.equals(s.getPrev().getW())) {
                        pq.insert(new SearchNode(n, s.getNumMove() + 1, s));
                    }


//                    if (s.getPrev() == null || (s.getPrev() != null && n.equals(s.getPrev().getW()))) {
//                        pq.insert(new SearchNode(n, s.getNumMove() + 1, s));
//                    }
                }
            }
        }
    }

    public int moves() {
        return numMoves;
    }

    public Iterable<WorldState> solution() {
        return solution;
    }
}
