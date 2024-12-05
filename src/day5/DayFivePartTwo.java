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

public class DayFivePartTwo {

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
                    return makeValidAndGetMiddle(update, rules);
                }
            }
        }
        return 0;
    }

    private static int makeValidAndGetMiddle(String update, Map<Integer, Set<Integer>> rules) {
        String[] updatePages = update.split(",");
        int[] pages = new int[updatePages.length];
        for (int i = 0; i < updatePages.length; i++) {
            pages[i] = Integer.parseInt(updatePages[i]);
        }

        // we will fill this array with the pages as we go
        // you can determine the correct index for a given page based on how many rules it has about the pages that follow it
        // (since it is guaranteed there is a rule for every possible number combo)
        int[] validPages = new int[updatePages.length];
        for (int i = 0; i < pages.length; i++) {
            // "children" is all the pages that must come after
            Set<Integer> children = rules.getOrDefault(pages[i], new HashSet<>());
            int numRulesAboutChildren = 0;
            for (int j = 0; j < pages.length; j++) {
                if (i == j) continue;
                if (children.contains(pages[j])) numRulesAboutChildren++;
            }
            validPages[validPages.length - numRulesAboutChildren - 1] = pages[i];
        }

        return validPages[validPages.length / 2];
    }
}
