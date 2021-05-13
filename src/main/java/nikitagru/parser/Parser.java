package nikitagru.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.DocumentType;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    private Pattern validURL = Pattern.compile("^(https?|http)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");
    private String url;

    private char[] separators = new char[] {' ', ',', '.', '!', '?','"', ';', ':', '[', ']', '(', ')',
            '\n', '\r', '\t'};

    public Parser(String url) {
        this.url = url;
    }

    public String[] parse() {
        Matcher matcher = validURL.matcher(url);
        if (matcher.find()) {
            Document doc = null;
            try {
                doc = Jsoup.connect(url).get();
            } catch (IOException e) {
                e.printStackTrace();
            }


            Element body = doc.body();
            Elements elementsWithText = body.getElementsMatchingText("[A-Za-zА-Яа-яЁё]|[0-9]");

            String text = elementsWithText.text();

            String[] words = text.split("[ ,.!?\";:\\[\\]()\\n\\t\\r]");

            return words;
        } else {
            System.out.println("You wrote invalid url. Please try again. Example of valid url: https://www.simbirsoft.com/");
        }
        return null;

    }
}
