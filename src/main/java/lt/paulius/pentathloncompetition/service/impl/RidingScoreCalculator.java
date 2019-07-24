package lt.paulius.pentathloncompetition.service.impl;

import lombok.Data;
import lt.paulius.pentathloncompetition.entity.Athlete;
import lt.paulius.pentathloncompetition.entity.Riding;
import lt.paulius.pentathloncompetition.service.api.ScoreCalculator;

@Data
public class RidingScoreCalculator implements ScoreCalculator {

    public static final int FAULT_FREE_POINTS = 1200;

    public static final int KNOCKING_DOWN_PENALTY_POINTS = 28;

    public static final int REFUSAL_PENALTY_POINTS = 40;

    public static final int DISOBEDIENCE_LEADING_PENALTY_POINTS = 60;

    @Override
    public int calculateScore(Athlete athlete) {
        Riding riding = athlete.getRiding();

        int score = FAULT_FREE_POINTS;
        score -= riding.getKnockingDown() * KNOCKING_DOWN_PENALTY_POINTS;
        score -= riding.getRefusal() * REFUSAL_PENALTY_POINTS;
        score -= riding.getDisobedienceLeading() * DISOBEDIENCE_LEADING_PENALTY_POINTS;

        return score;
    }
}
