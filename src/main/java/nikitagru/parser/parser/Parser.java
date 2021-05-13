package nikitagru.parser.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/***
 * Main parse class
 */
public class Parser {

    private Pattern validURL = Pattern.compile("^(https?|http)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");     // valid url
    private String url;

    private Logger logger = Logger.getLogger("ErrorLog");
    private String appPath = new File(".").getCanonicalPath();      //application root path
    private FileHandler handler = new FileHandler(appPath + "/log/ErrorLogFile.log");       //logs path


    /***
     * Parser class constructor
     * @param url URL of HTML page
     * @throws IOException
     */
    public Parser(String url) throws IOException {
        this.url = url;
        logger.addHandler(handler);
        SimpleFormatter formatter = new SimpleFormatter();
        handler.setFormatter(formatter);
    }

    /***
     * Parse HTML page from url, which sends in constructor
     * @return array of all words
     */
    public String[] parse() {
        Matcher matcher = validURL.matcher(url);
        if (matcher.find()) {       //checks if user entered valid url
            Document doc;       //HTML page
            try {
                doc = Jsoup.connect(url).get();
                Element body = doc.body();      //return <body> of HTML page
                Elements elementsWithText = body.getElementsMatchingText("[A-Za-zА-Яа-яЁё]|[0-9]");

                String text = elementsWithText.text();      //get all text from page

                String[] words = text.split("[ ,.!«»'?\";:\\[\\]()\\n\\t\\r]|[\\u202F\\u00A0]|[\\u2014]");      // splitters

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
