package lab11.graphs;

//import sun.awt.image.ImageWatched;

import java.util.LinkedList;
import java.util.Queue;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private int t;
    private Maze maze;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        // Add more variables here!
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs(int v) {
        Queue<Integer> q = new LinkedList<>();
        q.add(v);
        marked[v] = true;
        while(!q.isEmpty()) {
            int vertex = q.remove();
            if (vertex == t) {
                return;
            }
            for (int n: maze.adj(vertex)) {
                if (!marked[n]) {
                    marked[n] = true;
                    edgeTo[n] = vertex;
                    distTo[n] = distTo[vertex] + 1;
                    announce();
//                    if (n == t) {
//                        return;
//                    }
                    q.add(n);
                }
            }
        }
    }


    @Override
    public void solve() {
         bfs(s);
    }
}

