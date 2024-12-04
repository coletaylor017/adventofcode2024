package day.two;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Implementation of day 2 part 2 that uses lists instead of primitive arrays.
 */
public class DayTwoPartTwoAlt {
    public static void main(String[] args) throws FileNotFoundException {
        int safeReports = 0;
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader("src/day/two/input")))) {
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(" ");
                List<Integer> numbers = new ArrayList<>();
                for (int i = 0; i < line.length; i++) {
                    numbers.add(Integer.parseInt(line[i]));
                }
                if (lineIsValidWithRemoval(numbers)) safeReports++;
            }
        }

        System.out.println(safeReports);
    }

    /**
     * A method to brute force the possible combinations for part 2.
     */
    private static boolean lineIsValidWithRemoval(List<Integer> numbers) {
        for (int i = 0; i < numbers.size(); i++) {
            List<Integer> numbersAlt = new ArrayList<>(numbers);
            // remove the element in numbersAlt
            numbersAlt.remove(i);
            if (lineIsValid(numbersAlt)) return true;
        }
        return false;
    }

    private static boolean lineIsValid(List<Integer> numbers) {
        boolean lineIsIncreasing = false;
        for (int i = 0; i < numbers.size() - 1; i++) { // skip the last number
            int a = numbers.get(i);
            int b = numbers.get(i + 1);

            // check increasing/decreasing
            if (i == 0) lineIsIncreasing = a < b;
            else if (lineIsIncreasing != (a < b)) return false;

            // check the delta
            int delta = Math.abs(a - b);
            if (delta < 1 || delta > 3) return false;
        }
        return true;
    }
}
