package lt.paulius.pentathloncompetition.service.impl;

import lombok.Data;
import lt.paulius.pentathloncompetition.entity.Athlete;
import lt.paulius.pentathloncompetition.entity.Shooting;
import lt.paulius.pentathloncompetition.service.api.ScoreCalculator;

@Data
public class ShootingScoreCalculator implements ScoreCalculator {

    public static final int _1000_POINTS = 1000;

    public static final int _1000_POINTS_SCORE = 172;

    public static final int _1_POINT_DIFFERENCE_SCORE = 12;

    @Override
    public int calculateScore(Athlete athlete) {
        Shooting shooting = athlete.getShooting();
        int targetScore = shooting.getTargetScore();
        return _1000_POINTS + (targetScore - _1000_POINTS_SCORE) * _1_POINT_DIFFERENCE_SCORE;
    }
}
