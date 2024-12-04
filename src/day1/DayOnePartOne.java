package day1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class DayOnePartOne {

    public static void main(String[] args) throws FileNotFoundException {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader("src/day1/input")))) {
            while (scanner.hasNextInt()) {
                list1.add(scanner.nextInt());
                list2.add(scanner.nextInt());
            }
        }

        Collections.sort(list1);
        Collections.sort(list2);

        int sum = 0;
        for (int i = 0; i < list1.size(); i++) {
            sum += Math.abs(list1.get(i) - list2.get(i));
        }

        System.out.println(sum);
    }
}