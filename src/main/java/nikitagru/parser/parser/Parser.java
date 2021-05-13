package nikitagru.parser.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.DocumentType;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.lang.System.Logger.Level.*;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    private Pattern validURL = Pattern.compile("^(https?|http)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");
    private String url;

    private Logger logger = Logger.getLogger("ErrorLog");
    private FileHandler handler = new FileHandler("log/ErrorLogFile.log");


    public Parser(String url) throws IOException {
        this.url = url;
        logger.addHandler(handler);
        SimpleFormatter formatter = new SimpleFormatter();
        handler.setFormatter(formatter);
    }

    public String[] parse() {
        Matcher matcher = validURL.matcher(url);
        if (matcher.find()) {
            Document doc;
            try {
                doc = Jsoup.connect(url).get();
                Element body = doc.body();
                Elements elementsWithText = body.getElementsMatchingText("[A-Za-zА-Яа-яЁё]|[0-9]");

                String text = elementsWithText.text();

                String[] words = text.split("[ ,.!«»'?\";:\\[\\]()\\n\\t\\r]|[\\u202F\\u00A0]|[\\u2014]");

                return words;
            } catch (UnknownHostException e) {
                System.out.println("You have written unknown host name");
                logger.log(Level.WARNING, "wrote unknown url - " + url);
            } catch (IOException ex) {
                ex.printStackTrace();
                logger.log(Level.WARNING, "can't parse html page - " + url);
            }
        } else {
            System.out.println("You have written invalid url. Please try again. Example of valid url: https://www.simbirsoft.com/");
            logger.log(Level.WARNING, "wrote invalid url - " + url);
        }
        return null;

    }
}
