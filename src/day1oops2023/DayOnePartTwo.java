package day1oops2023;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;
import java.util.Scanner;

public class DayOnePartTwo {

    private static final Map<String, Character> numbers = Map.of(
            "one", '1',
            "two", '2',
            "three", '3',
            "four", '4',
            "five", '5',
            "six", '6',
            "seven", '7',
            "eight", '8',
            "nine", '9');
    public static void main(String[] args) throws FileNotFoundException {
        int total = 0;
        String filePath = new File("").getAbsolutePath();
        System.out.println(filePath);
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader("src/day1/input")))) {
            while (scanner.hasNext()) {
                // break the string into an array of chars
                String line = scanner.nextLine();
                char[] chars = line.toCharArray();

                // locate the first and last digits
                int firstDigitPosition = Integer.MAX_VALUE;
                for (int i = 0; i < chars.length; i++) {
                    if (Character.isDigit(chars[i])) {
                        firstDigitPosition = i;
                        break;
                    }
                }

                int lastDigitPosition = chars.length - 1;
                for (int i = chars.length - 1; i >= 0; i--) {
                    if (Character.isDigit(chars[i])) {
                        lastDigitPosition = i;
                        break;
                    }
                }

                // once we've obtained the locations of the first and last digits, see if we can do better with the string numbers
                int firstStringNumberPosition = Integer.MAX_VALUE;
                int lastStringNumberPosition = Integer.MIN_VALUE;
                char firstStringNumberValue = '0';
                char lastStringNumberValue = '0';
                for (String number : numbers.keySet()) {
                    int firstIdx = line.indexOf(number);
                    int lastIdx = line.lastIndexOf(number);
                    // if string is found, check if it's the current best for first and/or last
                    if (firstIdx != -1 && firstIdx < firstStringNumberPosition) {
                        firstStringNumberPosition = firstIdx;
                        firstStringNumberValue = numbers.get(number);
                    }
                    if (lastIdx != -1 && lastIdx > lastStringNumberPosition) {
                        lastStringNumberPosition = lastIdx;
                        lastStringNumberValue = numbers.get(number);
                    }
                }

                // compare the digits to the string numbers, which is better?
                boolean useStringNumberAsLast = lastStringNumberPosition > lastDigitPosition;
                boolean useStringNumberAsFirst = firstStringNumberPosition < firstDigitPosition;
                char bestFirstNumber = useStringNumberAsFirst ? firstStringNumberValue : chars[firstDigitPosition];
                char bestLastNumber = useStringNumberAsLast ? lastStringNumberValue : chars[lastDigitPosition];

                int lineTotal = Integer.parseInt("" + bestFirstNumber + bestLastNumber);
                System.out.println(lineTotal);
                total += lineTotal;
            }
        }
        System.out.println(total);
    }
}