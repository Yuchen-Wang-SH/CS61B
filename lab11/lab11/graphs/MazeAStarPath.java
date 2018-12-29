package lab11.graphs;

import edu.princeton.cs.algs4.IndexMinPQ;
import sun.security.provider.certpath.Vertex;

import java.util.PriorityQueue;

/**
 *  @author Josh Hug
 */
public class MazeAStarPath extends MazeExplorer {
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;
    private IndexMinPQ<Integer> pq;


    public MazeAStarPath(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        for (int i = 0; i < distTo.length; i++) {
            distTo[i] = Integer.MAX_VALUE;
        }
        distTo[s] = 0;
        edgeTo[s] = s;
        pq = new IndexMinPQ<>(maze.V());
    }

    /** Estimate of the distance from v to the target. */
    private int h(int v) {
        return Math.abs(maze.toX(v) - maze.toX(t)) +
                Math.abs(maze.toY(v) - maze.toY(t));
    }

    /** Finds vertex estimated to be closest to target. */
    private int findMinimumUnmarked() {
        return -1;
        /* You do not have to use this method. */
    }

    /** Performs an A star search from vertex s. */
    private void astar(int s) {
        pq.insert(s, 0);
        while (!pq.isEmpty()) {
            relax(pq.delMin());
            if (targetFound) {
                return;
            }
        }
    }

    private void relax(int v) {
        for (int n: maze.adj(v)) {
            marked[n] = true;
            if (distTo[n] > distTo[v] + 1) {
                distTo[n] = distTo[v] + 1;
                edgeTo[n] = v;
                announce();
                if (pq.contains(n)) {
                    pq.changeKey(n, distTo[n] + h(n));
                } else {
                    pq.insert(n, distTo[n] + h(n));
                }
            }
            if (n == t) {
                targetFound = true;
                return;
            }
        }
    }

    @Override
    public void solve() {
        astar(s);
    }

}

