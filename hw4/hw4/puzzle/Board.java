package hw4.puzzle;

import edu.princeton.cs.algs4.Queue;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.swing.*;

public class Board implements WorldState {
    private int[][] tiles;
    private static final int BLANK = 0;
    private int hamming, manhattan;

    /** Constructs a board from an N-by-N array of tiles where
     * tiles[i][j] = tile at row i, column j
     */
    public Board(int[][] tiles) {
        this.tiles = new int[tiles.length][tiles[0].length];
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                this.tiles[i][j] = tiles[i][j];
            }
        }
        hamming = -1;
        manhattan = -1;
    }

    /**Returns value of tile at row i, column j (or 0 if blank)*/
    public int tileAt(int i, int j) {
        if (i < 0 || i > size() -1 || j < 0 || j > size() - 1) {
            throw new IndexOutOfBoundsException("Illegal i or j.");
        }
        return tiles[i][j];
    }
    /** Returns the board size N */
    public int size() {
        return tiles[0].length;
    }

    /** Borrowed from http://joshh.ug/neighbors.html */
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == BLANK) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = BLANK;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = BLANK;
                }
            }
        }
        return neighbors;
    }

    public int hamming() {
        if (hamming == -1) {
            int sum = 0;
            for (int i = 0; i < size(); i++) {
                for (int j = 0; j < size(); j++) {
                    if (tiles[i][j] != BLANK) {
                        sum += hammingSingle(tiles[i][j], i, j);
                    }
                }
            }
            hamming = sum;
        }
        return hamming;
    }

    private int hammingSingle(int value, int i, int j) {
        if (value != 1 + i * size() + j) {
            return 1;
        }
        return 0;
    }

    public int manhattan() {
        if (manhattan == -1) {
            int sum = 0;
            for (int i = 0; i < size(); i++) {
                for (int j = 0; j < size(); j++) {
                    if (tiles[i][j] != BLANK) {
                        sum += manhattamSingle(tiles[i][j], i, j);
                    }
                }
            }
            manhattan = sum;
        }
        return manhattan;
    }

    private int manhattamSingle(int value, int i, int j) {
        return Math.abs(i - tileToX(value)) + Math.abs(j - tileToY(value));
    }

    private int tileToX(int tile) {
        return (tile - 1) / size();
    }

    private int tileToY(int tile) {
        return (tile - 1) % size();
    }

    @Override
    public int estimatedDistanceToGoal() {
        return manhattan();
    }

    @Override
    public boolean equals(Object y) {
        if (this == y) {
            return true;
        }
        if (y == null || this.getClass() != y.getClass()) {
            return false;
        }
        Board that = (Board) y;
        if (size() != that.size()) {
            return false;
        }
        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < size(); j++) {
                if (tileAt(i, j) != that.tileAt(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    /** Returns the string representation of the board. 
      * Uncomment this method. */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

//    public static void main(String[] args) {
//        int[][] t = {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};
//        Board b = new Board(t);
//        System.out.println(b.hamming());
//        System.out.println(b.manhattan());
//    }
}
