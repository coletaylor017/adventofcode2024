package day1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class DayOnePartOne {
    public static void main(String[] args) throws FileNotFoundException {
        int total = 0;
        String filePath = new File("").getAbsolutePath();
        System.out.println(filePath);
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader("src/day1/input")))) {
            while (scanner.hasNext()) {
                // break the string into an array of chars
                char[] chars = scanner.nextLine().toCharArray();
                int i = 0;
                while (!Character.isDigit(chars[i])) {
                    i++;
                }

                int j = chars.length - 1;
                while (!Character.isDigit(chars[j])) {
                    j--;
                }
                int lineTotal = Integer.parseInt("" + chars[i] + chars[j]);
                System.out.println(lineTotal);
                total += lineTotal;
            }
        }
        System.out.println(total);
    }
}