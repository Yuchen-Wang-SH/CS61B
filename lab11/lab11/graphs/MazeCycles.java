package lab11.graphs;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private Maze maze;
    private boolean found;
    private int[] invisibleEdgeTo;

    public MazeCycles(Maze m) {
        super(m);
        maze = m;
        found = false;
        invisibleEdgeTo = new int[edgeTo.length];
    }

    @Override
    public void solve() {
        /** 1. Select a not-yet-visited vertex to start search. If all visited, return.
         * 2. run dfs on this vertex to search for circle, if found, return.
         * 3.if not found, goto 1.
         */
        while (true) {
            int vToStart = findNotVisited(marked);
            // If all vertices are visited, return -1, else return index.
            if (vToStart == -1) {
                return;
            }
            findCircle(vToStart, -1);
            if (found) {
                return;
            }
        }
    }

    private static int findNotVisited(boolean[] marked) {
        /** Loop over marked. Return the index of the first element whose value
         * is false. If all are true, return -1.
         */
        for (int i = 0; i < marked.length; i++) {
            if (!marked[i]) {
                return i;
            }
        }
        return -1;
    }

    private void findCircle(int v, int parentV) {
        /** Run dfs. If a neighbor is visited and it is not the parent of this current
         * vertex, there is a circle. Print the circle and return true. Otherwise,
         * return false.
         */
        marked[v] = true;
        announce();
        for (int n: maze.adj(v)) {
            if (marked[n]) {
                if (n != parentV) {
                    found = true;
                    invisibleEdgeTo[n] = v;
                    drawCircle(n);
                    return;
                } else {
                    continue;
                }
            }
            invisibleEdgeTo[n] = v;
            findCircle(n, v);
            if (found) {
                return;
            }
        }
    }

    private void drawCircle(int v) {
        // Draw the circle which starts and ends at v.
        int endPoint = v;
        while (true) {
            edgeTo[v] = invisibleEdgeTo[v];
            v = edgeTo[v];
            if (v == endPoint) {
                announce();
                return;
            }
        }
    }

    // Helper methods go here
}

