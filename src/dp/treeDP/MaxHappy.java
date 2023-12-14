package dp.treeDP;

import java.util.ArrayList;
import java.util.List;

/**
 * In company, every employee has a subordinates. Now the company wants to hold a party.
 * Every employee may or may not come to the party. If an employee comes to the party, his all subordinates will not come.
 * Every employee has a happy value. The company wants to maximize the sum of all employees' happy value.
 */
public class MaxHappy {

    public static class Employee {
        public int happy;
        public List<Employee> subordinates;

        public Employee(int h) {
            happy = h;
            subordinates = new ArrayList<>();
        }
    }

    public static int maxHappy(Employee boss) {
        Info allInfo = process(boss);
        return Math.max(allInfo.notCome, allInfo.come);
    }

    public static class Info{
        public int come;
        public int notCome;
        public Info(int no, int yes) {
            notCome = no;
            come = yes;
        }
    }

    public static Info process(Employee employee) {
        if (employee == null) return new Info(0, 0);

        int notCome = 0;
        int come = employee.happy;

        for (Employee subordinate : employee.subordinates) {
            Info nextInfo = process(subordinate);
            notCome += Math.max(nextInfo.notCome, nextInfo.come);
            come += nextInfo.notCome;
        }
        return new Info(notCome, come);
    }

    public static int bruteForce(Employee boss) {
        if (boss == null) return 0;
        return bruteForceProcess(boss, false);
    }

    // up: true -> the boss of current employee will come
    // up: false -> the boss of current employee will not come
    public static int bruteForceProcess(Employee cur, boolean up) {
        if (up) {
            int ans = 0;
            for (Employee next : cur.subordinates) {
                ans += bruteForceProcess(next, false);
            }
            return ans;
        } else {
            int p1 = cur.happy;
            int p2 = 0;
            for (Employee next : cur.subordinates) {
                p1 += bruteForceProcess(next, true);
                p2 += bruteForceProcess(next, false);
            }
            return Math.max(p1, p2);
        }
    }

    // for test
    public static Employee generateBoss(int maxLevel, int maxNexts, int maxHappy) {
        if (Math.random() < 0.02) {
            return null;
        }
        Employee boss = new Employee((int) (Math.random() * (maxHappy + 1)));
        generateNextEmployees(boss, 1, maxLevel, maxNexts, maxHappy);
        return boss;
    }

    // for test
    public static void generateNextEmployees(Employee e, int level, int maxLevel, int maxNexts, int maxHappy) {
        if (level > maxLevel) {
            return;
        }
        int nextsSize = (int) (Math.random() * (maxNexts + 1));
        for (int i = 0; i < nextsSize; i++) {
            Employee next = new Employee((int) (Math.random() * (maxHappy + 1)));
            e.subordinates.add(next);
            generateNextEmployees(next, level + 1, maxLevel, maxNexts, maxHappy);
        }
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxNexts = 7;
        int maxHappy = 100;
        int testTimes = 100000;
        System.out.println("Test begin: ");
        for (int i = 0; i < testTimes; i++) {
            Employee boss = generateBoss(maxLevel, maxNexts, maxHappy);
            if (maxHappy(boss) != bruteForce(boss)) {
                System.out.println("Test failed!");
            }
        }
        System.out.println("Test finished!");
    }
}
