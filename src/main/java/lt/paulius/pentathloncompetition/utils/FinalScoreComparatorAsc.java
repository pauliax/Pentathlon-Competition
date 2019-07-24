package lt.paulius.pentathloncompetition.utils;

import lt.paulius.pentathloncompetition.entity.Athlete;

import java.util.Comparator;

public class FinalScoreComparatorAsc implements Comparator<Athlete> {

    @Override
    public int compare(Athlete athlete1, Athlete athlete2) {
        String finalScore1 = athlete1.getFinalScoreTime();
        String finalScore2 = athlete2.getFinalScoreTime();

        return finalScore1.compareTo(finalScore2);
    }
}
