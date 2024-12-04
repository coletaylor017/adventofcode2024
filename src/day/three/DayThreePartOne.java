package day.three;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DayThreePartOne {
    public static void main(String[] args) throws IOException {
        int answer = 0;
        File inputFile = new File("src/day/three/input");
        String input = Files.readString(inputFile.toPath(), Charset.defaultCharset());
        Pattern pattern = Pattern.compile("mul\\((\\d{1,3}),(\\d{1,3})\\)");
        Matcher matcher = pattern.matcher(input);

        while (matcher.find())
        {
            int factor1 = Integer.parseInt(matcher.group(1));
            int factor2 = Integer.parseInt(matcher.group(2));
            answer += factor1 * factor2;
        }

        System.out.println(answer);
    }

}