package greedyAlgrithm;

import java.util.Arrays;
import java.util.Comparator;

public class BestArrangeProgram {

    public static class Program{
        public int start;
        public int end;

        public Program(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static class ProgramComparator implements Comparator<Program> {

        @Override
        public int compare(Program o1, Program o2) {
            return o1.end - o2.end;
        }
    }

    public static int bestArrange(Program[] programs) {
        Arrays.sort(programs, new ProgramComparator());
        int timeLine = 0;
        int result = 0;

        for (int i = 0; i < programs.length; i++) {
            if (timeLine <= programs[i].start) {
                result++;
                timeLine = programs[i].end;
            }
        }
        return result;
    }

    // test if the above algorithm is greater than brute force
    public static int testMethod(Program[] programs) {
        if (programs == null || programs.length == 0) return 0;

        return process(programs, 0, 0);
    }

    // put all the programs into the programs array
    // done is the number of programs that have been arranged
    // timeLine is the current time point
    private static int process(Program[] programs, int done, int timeLine) {
        if (programs.length == 0) return done;

        int max = done;

        for (int i = 0; i < programs.length; i++) {
            if (programs[i].start >= timeLine) {
                Program[] nextPrograms = copyButExcept(programs, i);
                max = Math.max(max, process(nextPrograms, done + 1, programs[i].end));
            }
        }
        return max;
    }

    private static Program[] copyButExcept(Program[] programs, int i) {
        Program[] ans = new Program[programs.length - 1];
        int index = 0;

        for (int j = 0; j < programs.length; j++) {
            if (j != i) {
                ans[index++] = programs[j];
            }
        }
        return ans;
    }

    // for test
    public static Program[] generatePrograms(int programSize, int timeMax) {
        Program[] ans = new Program[(int)(Math.random() * (programSize + 1))];
        for (int i = 0; i < ans.length; i++) {
            int r1 = (int) (Math.random() * (timeMax + 1));
            int r2 = (int) (Math.random() * (timeMax + 1));
            if (r1 == r2) {
                ans[i] = new Program(r1, r1 + 1);
            } else {
                ans[i] = new Program(Math.min(r1, r2), Math.max(r1, r2));
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int programSize = 12;
        int timeMax = 20;
        int timeTimes = 1000000;
        for (int i = 0; i < timeTimes; i++) {
            Program[] programs = generatePrograms(programSize, timeMax);
            if (bestArrange(programs) != testMethod(programs)) {
                System.out.println("Test failed");
            }
        }
        System.out.println("Test finish!");
    }
}
