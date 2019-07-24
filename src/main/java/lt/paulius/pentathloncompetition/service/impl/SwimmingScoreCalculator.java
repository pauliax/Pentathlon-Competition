package lt.paulius.pentathloncompetition.service.impl;

import lombok.Data;
import lt.paulius.pentathloncompetition.entity.Athlete;
import lt.paulius.pentathloncompetition.entity.Swimming;
import lt.paulius.pentathloncompetition.service.api.ScoreCalculator;
import lt.paulius.pentathloncompetition.utils.DateTimeUtils;

@Data
public class SwimmingScoreCalculator implements ScoreCalculator {

    public static final int _1000_POINTS = 1000;

    public static final String _1000_POINTS_TIME = "02:30.0";

    public static final int SEC_FRACTION_DIFFERENCE = 3;

    public static final int POINTS_DIFFERENCE = 4;

    public static final int _1000 = 1000;

    @Override
    public int calculateScore(Athlete athlete) {
        Swimming swimming = athlete.getSwimming();
        String time = swimming.getTime();

        int diffInMillis = DateTimeUtils.getDateDiffInMillis(time, _1000_POINTS_TIME);

        return _1000_POINTS - (diffInMillis * SEC_FRACTION_DIFFERENCE / _1000) * POINTS_DIFFERENCE;
    }
}
