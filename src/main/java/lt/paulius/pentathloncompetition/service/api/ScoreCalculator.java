package lt.paulius.pentathloncompetition.service.api;

import lt.paulius.pentathloncompetition.entity.Athlete;

public interface ScoreCalculator {

    int calculateScore(Athlete athlete);
}
