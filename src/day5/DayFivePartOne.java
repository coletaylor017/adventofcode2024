package day5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DayFivePartOne {

    private static final Pattern RULE_PATTERN = Pattern.compile("(\\d+)\\|(\\d+)");

    public static void main(String[] args) throws IOException {

        Map<Integer, Set<Integer>> rules = new HashMap<>();
        int answer = 0;

        try (BufferedReader br = new BufferedReader(new FileReader("src/day5/input"))) {
            String line;
            boolean readingRules = true;
            while ((line = br.readLine()) != null) {
                if(line.isEmpty()) { // transition from reading the rules to reading updates
                    readingRules = false;
                    continue;
                }
                if (readingRules) {
                    Matcher matcher = RULE_PATTERN.matcher(line);
                    matcher.find();
                    int page1 = Integer.parseInt(matcher.group(1));
                    int page2 = Integer.parseInt(matcher.group(2));
                    if (!rules.containsKey(page1)) rules.put(page1, new HashSet<>());
                    rules.get(page1).add(page2);
                }
                else {
                    answer += getMiddleIfValid(line, rules);
                }
            }
        }
        System.out.println(answer);
    }

    private static int getMiddleIfValid(String update, Map<Integer, Set<Integer>> rules) {
        String[] updatePages = update.split(",");
        for (int i = 0; i < updatePages.length; i++) {
            int page1 = Integer.parseInt(updatePages[i]);
            for (int j = i + 1; j < updatePages.length; j++) {
                int page2 = Integer.parseInt(updatePages[j]);
                if (rules.containsKey(page1) && !rules.get(page1).contains(page2)) {
                    return 0;
                }
            }
        }
        return Integer.parseInt(updatePages[updatePages.length / 2]);
    }
}
