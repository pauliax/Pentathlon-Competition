package lt.paulius.pentathloncompetition.service.impl;

import lt.paulius.pentathloncompetition.PentathlonCompetitionApplicationTests;
import lt.paulius.pentathloncompetition.entity.Athlete;
import lt.paulius.pentathloncompetition.entity.Riding;
import lt.paulius.pentathloncompetition.service.api.ScoreCalculator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RidingScoreCalculatorTest extends PentathlonCompetitionApplicationTests {

    private ScoreCalculator calculator;

    private Athlete athlete;

    @Before
    public void setUp() {
        calculator = new RidingScoreCalculator();
        athlete = new Athlete();
    }

    @Test
    public void correctlyCalculatesRidingScoreWhenThereAreNoFaults() {
        athlete.setRiding(new Riding());

        int expectedScore = 1200;
        int score = calculator.calculateScore(athlete);

        assertEquals(expectedScore, score);
    }

    @Test
    public void correctlyCalculatesRidingScoreWhenThereAreFaults() {
        athlete.setRiding(new Riding(1, 2, 3));

        int expectedScore = 912;
        int score = calculator.calculateScore(athlete);

        assertEquals(expectedScore, score);
    }
}