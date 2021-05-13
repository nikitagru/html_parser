package nikitagru.parser;

import org.jsoup.nodes.Document;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

@SpringBootApplication
public class HtmlparserApplication {
	public static void main(String[] args) throws IOException {
		SpringApplication.run(HtmlparserApplication.class, args);

		Parser parser = new Parser("https://www.simлолпропормbirsoft.com/");
		String[] words = parser.parse();

		if (words != null) {
			Statistics statistics = new Statistics();
			HashMap<String, Integer> stats = statistics.getStatistics(words);

			for (String word : stats.keySet()) {
				System.out.println(word + " " + "-" + " " + stats.get(word));
			}
		}

	}
}
