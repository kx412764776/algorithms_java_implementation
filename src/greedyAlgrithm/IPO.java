package greedyAlgrithm;

import java.util.Comparator;
import java.util.PriorityQueue;

public class IPO {

    // K: the number of projects you can choose
    // W: the initial capital
    public static int findMaximizedCapital(int K, int W, int[] Profits, int[] Capitals) {
        PriorityQueue<Program> minCostHeap = new PriorityQueue<>(new MinCostComparator());
        PriorityQueue<Program> maxProfitHeap = new PriorityQueue<>(new MaxProfitComparator());
        for (int i = 0; i < Profits.length; i++) {
            minCostHeap.add(new Program(Profits[i], Capitals[i]));
        }

        for (int i = 0; i < K; i++) {
            while (!minCostHeap.isEmpty() && minCostHeap.peek().cost <= W) {
                maxProfitHeap.add(minCostHeap.poll());
            }
            if (maxProfitHeap.isEmpty()) {
                return W;
            }
            W += maxProfitHeap.poll().profit;
        }
        return W;
    }

    public static class Program {
        public int profit;
        public int cost;

        public Program(int profit, int cost) {
            this.profit = profit;
            this.cost = cost;
        }
    }
    public static class MinCostComparator implements Comparator<Program> {
        @Override
        public int compare(Program o1, Program o2) {
            return o1.cost - o2.cost;
        }
    }

    public static class MaxProfitComparator implements Comparator<Program> {
        @Override
        public int compare(Program o1, Program o2) {
            return o2.profit - o1.profit;
        }
    }
}
