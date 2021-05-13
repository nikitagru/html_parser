package nikitagru.parser;

import org.jsoup.nodes.Document;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.URL;

@SpringBootApplication
public class HtmlparserApplication {
	public static void main(String[] args) throws IOException {
		SpringApplication.run(HtmlparserApplication.class, args);

		Parser parser = new Parser("https://ru.wikipedia.org/wiki/Самолет");
		parser.parse();


	}
}
