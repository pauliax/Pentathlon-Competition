package lt.paulius.pentathloncompetition.service.impl;

import lt.paulius.pentathloncompetition.PentathlonCompetitionApplicationTests;
import lt.paulius.pentathloncompetition.entity.Athlete;
import lt.paulius.pentathloncompetition.entity.Swimming;
import lt.paulius.pentathloncompetition.service.api.ScoreCalculator;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SwimmingScoreCalculatorTest extends PentathlonCompetitionApplicationTests {

    private ScoreCalculator calculator;

    private Athlete athlete;

    @Before
    public void setUp() {
        calculator = new SwimmingScoreCalculator();
        athlete = new Athlete();
    }

    @Test
    public void correctlyCalculatesSwimmingScoreWhenTimeIs2mins30secs() {
        athlete.setSwimming(new Swimming("02:30.0"));

        int expectedScore = 1000;
        int score = calculator.calculateScore(athlete);

        assertEquals(expectedScore, score);
    }

    @Test
    public void correctlyCalculatesSwimmingScoreWhenTimeIsMoreThan2mins30secs() {
        athlete.setSwimming(new Swimming("02:40.0"));

        int expectedScore = 880;
        int score = calculator.calculateScore(athlete);

        assertEquals(expectedScore, score);
    }

    @Test
    public void correctlyCalculatesSwimmingScoreWhenTimeIsLessThan2mins30secs() {
        athlete.setSwimming(new Swimming("02:10.0"));

        int expectedScore = 1240;
        int score = calculator.calculateScore(athlete);

        assertEquals(expectedScore, score);
    }
}