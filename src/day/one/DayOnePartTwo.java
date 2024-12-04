package day.one;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class DayOnePartTwo {

    public static void main(String[] args) throws FileNotFoundException {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        Map<Integer, Integer> list2Counts = new HashMap<>();

        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader("src/day/one/input")))) {
            while (scanner.hasNextInt()) {
                list1.add(scanner.nextInt());
                int list2Number = scanner.nextInt();
                list2.add(list2Number);
                if (list2Counts.containsKey(list2Number)) {
                    list2Counts.put(list2Number, list2Counts.get(list2Number) + 1);
                } else {
                    list2Counts.put(list2Number, 1);
                }
            }
        }

        int sum = 0;
        for (int num : list1) {
            if (!list2Counts.containsKey(num)) {
                continue;
            }
            sum += num * list2Counts.get(num);
        }

        System.out.println(sum);
    }
}