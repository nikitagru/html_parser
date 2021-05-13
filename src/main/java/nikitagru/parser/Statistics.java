package nikitagru.parser;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Statistics {
    private HashMap<String, Integer> wordsStatistics = new HashMap<>();

    public HashMap<String, Integer> getStatistics(String[] words) {
        String[] wordsWithoutSym = deleteSymbols(words);
        for (int i = 0; i < wordsWithoutSym.length; i++) {
            if (!wordsStatistics.containsKey(wordsWithoutSym[i]) && !wordsWithoutSym[i].equals("")) {
                String currentWord = wordsWithoutSym[i];
                int count = 0;
                for (int j = 0; j < wordsWithoutSym.length; j++) {
                    if (wordsWithoutSym[j].equals(currentWord)) {
                        count++;
                    }
                }
                wordsStatistics.put(currentWord, count);
            }
        }
        return wordsStatistics;
    }

    private String[] deleteSymbols(String[] words) {
        Pattern pattern = Pattern.compile("[a-zA-Zа-яА-Я]+");


        for (int i = 0; i < words.length; i++) {
            Matcher matcher = pattern.matcher(words[i]);

            if (!matcher.find()) {
                words[i] = "";
            }
        }
        return words;
    }
}
