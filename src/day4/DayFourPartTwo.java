package day4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;

public class DayFourPartTwo {

    /**
     * This lazy modification to part one is hot garbage but solves the problem.
     */
    public static void main(String[] args) throws IOException {
        int answer = 0;
        File inputFile = new File("src/day4/input");
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        int lineLength = reader.readLine().length();
        String input = Files.readString(inputFile.toPath(), Charset.defaultCharset()).strip();
        input = input.replaceAll("\\n", "");

        for (int i = 0; i < input.length(); i++) {
            boolean masDownRight = (checkForMatch(input, "AM", lineLength, i, -1, -1)
                    && checkForMatch(input, "AS", lineLength, i, 1, 1));
            boolean masUpLeft = (checkForMatch(input, "AS", lineLength, i, -1, -1)
                    && checkForMatch(input, "AM", lineLength, i, 1, 1));
            boolean masUpRight = (checkForMatch(input, "AM", lineLength, i, -1, 1)
                    && checkForMatch(input, "AS", lineLength, i, 1, -1));
            boolean masDownLeft = (checkForMatch(input, "AS", lineLength, i, -1, 1)
                    && checkForMatch(input, "AM", lineLength, i, 1, -1));
            if ((masDownRight || masUpLeft) && (masUpRight || masDownLeft)) {
                answer++;
            }
        }

        System.out.println(answer);
    }

    private static boolean checkForMatch(String wordSearch, String toFind, int lineLength, int idx, int deltaX, int deltaY) {
        char letter = wordSearch.charAt(idx);
        if (letter != toFind.charAt(0)) {
            return false;
        }

        // get the (zero-based) horizontal and vertical coords of the letter
        int horizontalPosition = idx % lineLength;
        int verticalPosition = idx / lineLength; // will floor

        for (int i = 0, x = 0, y = 0;
             i < toFind.length();
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
                            || wordSearch.charAt(indexToSearchAt) != toFind.charAt(i)
            ) {
                return false;
            }
        }
        return true;
    }
}
