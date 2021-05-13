package nikitagru.parser;

import nikitagru.parser.parser.Parser;
import nikitagru.parser.statistics.Statistics;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;

@SpringBootApplication
public class HtmlparserApplication {
	public static void main(String[] args) throws IOException {
		SpringApplication.run(HtmlparserApplication.class, args);

		Parser parser = new Parser(args[0]);		// if application start from IDE you must change args[0] to your URL
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
