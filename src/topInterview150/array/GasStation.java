package topInterview150.array;

/**
 * 134. Gas Station<br>
 * There are n gas stations along a circular route, where the amount of gas at the ith station is gas[i].<br>
 * You have a car with an unlimited gas tank, and it costs cost[i] of gas to travel from the ith station to its next (i + 1)th station.<br>
 * You begin the journey with an empty tank at one of the gas stations.<br>
 * {@code Param} integer arrays gas and cost<br>
 * {@code Return} the starting gas station's index if you can travel around the circuit once in the clockwise direction, otherwise return -1.<br>
 * If there exists a solution, it is guaranteed to be unique<br>
 * {@code Link} <a href="https://leetcode.com/problems/gas-station/">Gas Station</a><br>
 */
public class GasStation {

    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int curr = 0; // current surplus gas
        int total = 0; // total surplus gas
        int start = 0; // starting point

        for (int i = 0; i < gas.length; i++) {
            curr += gas[i] - cost[i];
            if (curr < 0) {
                curr = 0;
                start = i + 1;
            }
            total += gas[i] - cost[i];
        }

        return total >= 0 ?  start : -1;
    }

    public static void main(String[] args) {
        int[] gas = {1,2,3,4,5};
        int[] cost = {3,4,5,1,2};
        System.out.println(canCompleteCircuit(gas, cost));

        int[] gas2 = {2,3,4};
        int[] cost2 = {3,4,3};
        System.out.println(canCompleteCircuit(gas2, cost2));
    }
}
