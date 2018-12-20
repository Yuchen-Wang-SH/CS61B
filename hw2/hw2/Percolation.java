package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.Arrays;

public class Percolation {
    private boolean[][] m;
    private int N;
    private WeightedQuickUnionUF disSet;
    private int numOpened;

    private int UPPER, LOWER;

    public Percolation(int N) {
        this.N = N;
        m = new boolean[N][N];
        // N*N is virtual upper node, N*N+1 lower.
        disSet = new WeightedQuickUnionUF(N*N+2);
        UPPER = N * N;
        LOWER = N * N + 1;
        numOpened = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) m[i][j] = false;
        }
    }

    private int xyTo1D(int row, int col) {
        return row * N + col;
    }


    public void open(int row, int col) {
        if (!m[row][col]) {
            m[row][col] = true;
            numOpened++;

            // If this is at the top layer, connect it to the virtual upper node.
            if (row == 0) {
                disSet.union(xyTo1D(row, col), UPPER);
            }

            // If bottom layer, then connect it to LOWER.
            if (row == N-1) {
                disSet.union(xyTo1D(row, col), LOWER);
            }

            // Connect with the opened holes of your neighbors.
            int[][] neighbors = neighbors(row, col);
            for (int[] neighbor: neighbors) {
                if (neighbor[0] != -1) {
                    if (!isOpen(neighbor[0], neighbor[1])) continue;
                    disSet.union(xyTo1D(row, col), xyTo1D(neighbor[0], neighbor[1]));
                }
            }
        }
    }

    private int[][] neighbors(int row, int col) {
        // Return the (row, col) index of valid neighbors of (row, col).
        // Invalid index will be （-1, -1).
        int[][] n = new int[4][2];
        for (int i = 0; i < 4; i++) Arrays.fill(n[i], -1);
        if (row + 1 < N) {
            n[0][0] = row + 1;
            n[0][1] = col;
        }
        if (row - 1 >= 0) {
            n[1][0] = row - 1;
            n[1][1] = col;
        }
        if (col + 1 < N) {
            n[2][0] = row;
            n[2][1] = col + 1;
        }
        if (col - 1 >=0) {
            n[3][0] = row;
            n[3][1] = col - 1;
        }
        return n;
    }

    public boolean isOpen(int row, int col) {
        return m[row][col];
    }

    public boolean isFull(int row, int col) {
//        // If any of the top layer ocean is connected to this position
//        // then it is full.
//        // And, it should be open to be full.
//        if (!isOpen(row, col)) return false;
//        for (int c = 0; c < N; c++) {
//            if (disSet.connected(xyTo1D(row, col), xyTo1D(0, c))) return true;
//        }
//        return false;

        // Only have to check whether this node is connected to the upper node.
        return disSet.connected(xyTo1D(row, col), UPPER);
    }

    public int numberOfOpenSites() {
        return numOpened;
    }

    public boolean percolates() {
        // If any of the bottom layer is full, then percolated.
//        for (int c = 0; c < N; c++) {
//            if (isFull(N-1, c)) return true;
//        }
//        return false;

        // You only have to check whether UPPER is connected to LOWER.
        return disSet.connected(UPPER, LOWER);
    }

    public static void main(String[] args) {
        Percolation p = new Percolation(10);
        int[][] n = p.neighbors(9, 0);
        for (int[] s: n) System.out.println(Arrays.toString(s));
    }
}
