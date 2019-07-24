package lt.paulius.pentathloncompetition.service.impl;

import lombok.Data;
import lt.paulius.pentathloncompetition.entity.Athlete;
import lt.paulius.pentathloncompetition.entity.Fencing;
import lt.paulius.pentathloncompetition.service.api.ScoreCalculator;

@Data
public class FencingScoreCalculator implements ScoreCalculator {

    private Integer numberOfAthletes;

    private int victoriesToGet1000Points;

    public static final int _1000_POINTS = 1000;

    public static final int _1000_POINTS_WINNING_PERCENTAGE = 70;

    public static final int _1_VICTORY_DIFFERENCE_IN_POINTS = 40;

    public static final int _100_PERCENT = 100;

    public FencingScoreCalculator(Integer numberOfAthletes) {
        this.numberOfAthletes = numberOfAthletes;
        victoriesToGet1000Points = numberOfAthletes * _1000_POINTS_WINNING_PERCENTAGE / _100_PERCENT;
    }

    @Override
    public int calculateScore(Athlete athlete) {
        if (numberOfAthletes == null) {
            throw new NullPointerException("Number of athletes is not initialized!");
        }

        Fencing fencing = athlete.getFencing();
        int victories = fencing.getVictories();

        return _1000_POINTS + (victories - victoriesToGet1000Points) * _1_VICTORY_DIFFERENCE_IN_POINTS;
    }
}
