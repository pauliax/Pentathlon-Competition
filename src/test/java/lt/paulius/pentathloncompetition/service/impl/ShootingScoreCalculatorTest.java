package lt.paulius.pentathloncompetition.service.impl;

import lt.paulius.pentathloncompetition.PentathlonCompetitionApplicationTests;
import lt.paulius.pentathloncompetition.entity.Athlete;
import lt.paulius.pentathloncompetition.entity.Shooting;
import lt.paulius.pentathloncompetition.service.api.ScoreCalculator;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ShootingScoreCalculatorTest extends PentathlonCompetitionApplicationTests {

    private ScoreCalculator calculator;

    private Athlete athlete;

    @Before
    public void setUp() {
        calculator = new ShootingScoreCalculator();
        athlete = new Athlete();
    }

    @Test
    public void correctlyCalculatesShootingScoreWhenTargetIs172() {
        athlete.setShooting(new Shooting(172));

        int expectedScore = 1000;
        int score = calculator.calculateScore(athlete);

        assertEquals(expectedScore, score);
    }

    @Test
    public void correctlyCalculatesShootingScoreWhenTargetIsMoreThan172() {
        athlete.setShooting(new Shooting(200));

        int expectedScore = 1336;
        int score = calculator.calculateScore(athlete);

        assertEquals(expectedScore, score);
    }

    @Test
    public void correctlyCalculatesShootingScoreWhenTargetIsLessThan172() {
        athlete.setShooting(new Shooting(157));

        int expectedScore = 820;
        int score = calculator.calculateScore(athlete);

        assertEquals(expectedScore, score);
    }
}