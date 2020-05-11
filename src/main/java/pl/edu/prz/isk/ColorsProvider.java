package pl.edu.prz.isk;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ColorsProvider {
    public static String[] getColors(Integer numberOfColors) {
        final List<String> result = new ArrayList(numberOfColors);

        for (int i = 0; i < numberOfColors; i++) {
            result.add(Character.toString((char) (i + 97)));
        }

        return result.stream().toArray(String[]::new);
    }
}
