package nikitagru.parser.statistics;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/***
 * Main statistic class
 */
public class Statistics {
    private HashMap<String, Integer> wordsStatistics = new HashMap<>();     //all words with count

    /***
     * Returns statistic of words
     * @param words array of words
     * @return Map where key - word, value - his count
     */
    public HashMap<String, Integer> getStatistics(String[] words) {
        String[] wordsWithoutSym = deleteSymbols(words);        // deletes all symbols
        for (int i = 0; i < wordsWithoutSym.length; i++) {
            if (!wordsStatistics.containsKey(wordsWithoutSym[i]) && !wordsWithoutSym[i].equals("")) {       //if finds new word
                String currentWord = wordsWithoutSym[i];        // get new word
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

    /***
     * Delete symbols from array (delete all, what is not a word)
     * @param words array of words
     * @return new array without symbols
     */
    private String[] deleteSymbols(String[] words) {
        Pattern pattern = Pattern.compile("[a-zA-Zа-яА-Я]+");       // all words in latin and in cyrillic

        for (int i = 0; i < words.length; i++) {
            Matcher matcher = pattern.matcher(words[i]);

            if (!matcher.find()) {      // if find not a word
                words[i] = "";
            }
        }
        return words;
    }
}
