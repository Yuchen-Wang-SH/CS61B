package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private int N;
    private PercolationFactory pf;

    private double mean, std, lowCon, highCon;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        this.N = N;
        this.pf = pf;

        double[] results = new double[T];
        for (int i = 0; i < T; i++) {
            results[i] = oneTrial();
        }
        mean = StdStats.mean(results);
        std = StdStats.stddev(results);
        lowCon = mean - 1.96 * std / Math.sqrt(T);
        highCon = mean + 1.96 * std / Math.sqrt(T);
    }

    private double oneTrial() {
        Percolation p = pf.make(N);

        while (!p.percolates()) {
            int[] site = randomSite();
            p.open(site[0], site[1]);
        }
        return p.numberOfOpenSites() / (N * N);
    }

    private int[] randomSite() {
        // site[0]: row, site[1]: col
        int[] site = new int[2];
        for (int i = 0; i < 2; i++) {
            int index = StdRandom.uniform(N);
            site[i] = index;
        }
        return site;
    }

    public double mean() {
        return mean;
    }

    public double stddev() {
        return std;
    }

    public double confidenceLow() {
        return lowCon;
    }

    public double confidenceHigh() {
        return highCon;
    }
}
