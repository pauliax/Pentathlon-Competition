package lt.paulius.pentathloncompetition;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PentathlonCompetitionApplication {

	public static final String FILE_PATH = "data\\Athlete_Results.csv";

	public static final String TIME_FORMAT = "mm:ss.S";

	public static void main(String[] args) {
		SpringApplication.run(PentathlonCompetitionApplication.class, args);
	}
}
