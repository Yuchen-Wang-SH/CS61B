package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] m;
    private int N;
    private WeightedQuickUnionUF disSet;
    private int numOpened;

    public Percolation(int N) {
        this.N = N;
        m = new boolean[N][N];
        disSet = new WeightedQuickUnionUF(N*N);
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
            // Stuff about connect;
        }
    }

    public boolean isOpen(int row, int col) {
        return m[row][col];
    }

    public boolean isFull(int row, int col) {
        // If any of the top layer ocean is connected to this position
        // then it is full.
        // And, it should be open to be full.
        if (!isOpen(row, col)) return false;
        for (int c = 0; c < N; c++) {
            if (disSet.connected(xyTo1D(row, col), xyTo1D(0, c))) return true;
        }
        return false;
    }

    public int numberOfOpenSites() {
        return numOpened;
    }

    public boolean percolates() {
        // If any of the bottom layer is full, then percolated.
        for (int c = 0; c < N; c++) {
            if (isFull(N-1, c)) return true;
        }
        return false;
    }
}
