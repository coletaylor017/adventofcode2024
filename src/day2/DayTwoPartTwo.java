package day2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class DayTwoPartTwo {
    public static void main(String[] args) throws FileNotFoundException {
        int safeReports = 0;
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader("src/day2/input")))) {
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(" ");
                int[] numbers = new int[line.length];
                for (int i = 0; i < line.length; i++) {
                    numbers[i] = Integer.parseInt(line[i]);
                }
                if (lineIsValidWithRemoval(numbers)) safeReports++;
            }
        }

        System.out.println(safeReports);
    }

    /**
     * A method to brute force the possible combinations for part 2.
     */
    private static boolean lineIsValidWithRemoval(int[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            int[] numbersAlt = new int[numbers.length - 1];
            // remove the element in numbersAlt
            int j = 0;
            while(j < i) {
                numbersAlt[j] = numbers[j];
                j++;
            }
            while(j < numbersAlt.length) {
                numbersAlt[j] = numbers[j + 1];
                j++;
            }
            if (lineIsValid(numbersAlt)) return true;
        }
        return false;
    }

    private static boolean lineIsValid(int[] numbers) {
        boolean lineIsIncreasing = false;
        for (int i = 0; i < numbers.length - 1; i++) { // skip the last number
            int a = numbers[i];
            int b = numbers[i + 1];

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
