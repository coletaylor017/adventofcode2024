package day4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;

/**
 * The solution, but with comments stripped out and intermediate variables consolidated
 */
public class DayFourPartOneSmall {

    private static final String XMAS = "XMAS";


    public static void main(String[] args) throws IOException {
        int answer = 0;
        File inputFile = new File("src/day4/input");
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        int lineLength = reader.readLine().length();
        String input = Files.readString(inputFile.toPath(), Charset.defaultCharset()).strip();
        input = input.replaceAll("\\n", "");

        for (int i = 0; i < input.length(); i++) {
            for (int x = -1; x <= 1; x++) {
                for (int y = -1; y <= 1; y++) {
                    if (x == 0 && y == 0) continue;
                    if (checkForMatch(input, lineLength, i, x, y)) answer++;
                }
            }
        }

        System.out.println(answer);
    }

    private static boolean checkForMatch(String wordSearch, int lineLength, int idx, int deltaX, int deltaY) {
        for (int i = 0, x = 0, y = 0; i < XMAS.length(); i++, x += deltaX, y += deltaY) {
            int indexToSearchAt = idx + (y * lineLength) + x;
            if (
                indexToSearchAt % lineLength != idx % lineLength + x
                || indexToSearchAt / lineLength != idx / lineLength + y
                || indexToSearchAt < 0
                || indexToSearchAt >= wordSearch.length()
                || wordSearch.charAt(indexToSearchAt) != XMAS.charAt(i)
            ) return false;
        }
        return true;
    }
}
