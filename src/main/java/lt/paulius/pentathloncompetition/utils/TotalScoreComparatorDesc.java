package lt.paulius.pentathloncompetition.utils;

import lt.paulius.pentathloncompetition.entity.Athlete;

import java.util.Comparator;

public class TotalScoreComparatorDesc implements Comparator<Athlete> {

    @Override
    public int compare(Athlete athlete1, Athlete athlete2) {
        return athlete2.getTotalScore() - athlete1.getTotalScore();
    }
}
