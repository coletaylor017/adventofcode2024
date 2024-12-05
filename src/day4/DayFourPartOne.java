package day4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;

/**
 * A solution to day four part 1. This also will solve a word search input for any word, not just XMAS.
 */
public class DayFourPartOne {


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
        char letter = wordSearch.charAt(idx);
        if (letter != XMAS.charAt(0)) return false;

        // get the (zero-based) horizontal and vertical coords of the letter
        int horizontalPosition = idx % lineLength;
        int verticalPosition = idx / lineLength; // will floor

        for (int i = 0, x = 0, y = 0;
             i < XMAS.length();
             i++, x += deltaX, y += deltaY
        ) {
            // get the one-dimensional index we should look for the next letter at
            int indexToSearchAt = idx + (y * lineLength) + x;
            // get the 2-dimensional indices that the 1-dimensional index corresponds to
            int searchIndexHorizontalPosition = indexToSearchAt % lineLength;
            int searchIndexVerticalPosition = indexToSearchAt / lineLength;
            // the horizontal position should be equal to x * deltaX + horizontalPosition
            boolean horizontalPositionIsCorrect = searchIndexHorizontalPosition == horizontalPosition + x;
            // the vertical position should be equal to y * deltaY + verticalPosition
            boolean verticalPositionIsCorrect = searchIndexVerticalPosition == verticalPosition + y;
            boolean indexIsOutOfBounds = indexToSearchAt < 0 || indexToSearchAt >= wordSearch.length();
            if (
                !horizontalPositionIsCorrect
                || !verticalPositionIsCorrect
                || indexIsOutOfBounds
                || wordSearch.charAt(indexToSearchAt) != XMAS.charAt(i)
            ) return false;
        }
        return true;
    }
}
