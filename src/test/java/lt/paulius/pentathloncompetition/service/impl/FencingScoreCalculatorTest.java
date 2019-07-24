package lt.paulius.pentathloncompetition.service.impl;

import lt.paulius.pentathloncompetition.PentathlonCompetitionApplicationTests;
import lt.paulius.pentathloncompetition.entity.Athlete;
import lt.paulius.pentathloncompetition.entity.Fencing;
import lt.paulius.pentathloncompetition.service.api.ScoreCalculator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FencingScoreCalculatorTest extends PentathlonCompetitionApplicationTests {

    private ScoreCalculator calculator;

    private Athlete athlete;

    @Before
    public void setUp() {
        calculator = new FencingScoreCalculator(10);
        athlete = new Athlete();
    }

    @Test
    public void correctlyCalculatesFencingScoreWhenWinningPercentageIs70() {
        athlete.setFencing(new Fencing(7));

        int expectedScore = 1000;
        int score = calculator.calculateScore(athlete);

        assertEquals(expectedScore, score);
    }

    @Test
    public void correctlyCalculatesFencingScoreWhenWinningPercentageAbove70() {
        athlete.setFencing(new Fencing(8));

        int expectedScore = 1040;
        int score = calculator.calculateScore(athlete);

        assertEquals(expectedScore, score);
    }

    @Test
    public void correctlyCalculatesFencingScoreWhenWinningPercentageBelow70() {
        athlete.setFencing(new Fencing(5));

        int expectedScore = 920;
        int score = calculator.calculateScore(athlete);

        assertEquals(expectedScore, score);
    }
}