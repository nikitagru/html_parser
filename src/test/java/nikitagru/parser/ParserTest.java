package nikitagru.parser;


import nikitagru.parser.parser.Parser;
import org.junit.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    @Test
    public void simpleParse() throws IOException {
        Parser parser = new Parser("https://ru.wikipedia.org/wiki/Самолёт");
        String[] words = parser.parse();
        assertNotNull(words);
    }

    @Test
    public void simpleFalse() throws IOException {
        Parser parser = new Parser("https://ru.wikipegfdgdfsgtshtrhgfdia.org/wiki/Самолёт");
        String[] words = parser.parse();
        assertNull(words);
    }

    @Test
    public void invalidURL() throws IOException {
        Parser parser = new Parser("fdbgkjdfnglfbgjdf");
        String[] words = parser.parse();
        assertNull(words);
    }
}
