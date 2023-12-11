package arrays;

import java.util.LinkedList;

/**
 * Sliding window structure
 */
public class SlidingWindow {

    public static class WindowMax {
        private int L;
        private int R;
        private int[] arr;
        private LinkedList<Integer> subArrayMax;

        public WindowMax(int[] a) {
            arr = a;
            L = -1;
            R = 0;
            subArrayMax = new LinkedList<>();
        }

        public void AddNumFromRight() {
            if (R == arr.length) return;

            while (!subArrayMax.isEmpty() &&
                    arr[subArrayMax.peekLast()] <= arr[R]) {
                subArrayMax.pollLast();
            }
            subArrayMax.addLast(R);
            R++;
        }

        public void removeNumFromLeft() {
            if (L >= R - 1) return;
            L++;
            // remove the max element if it is out of the window
            if (!subArrayMax.isEmpty() &&
                    subArrayMax.peekFirst() == L) {
                subArrayMax.pollFirst();
            }
        }

        public Integer getMax() {
            if (!subArrayMax.isEmpty()) {
                return arr[subArrayMax.peekFirst()];
            }
            return null;
        }
    }
}
