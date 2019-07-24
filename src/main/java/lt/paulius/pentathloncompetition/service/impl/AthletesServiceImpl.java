package lt.paulius.pentathloncompetition.service.impl;

import lombok.Data;
import lt.paulius.pentathloncompetition.PentathlonCompetitionApplication;
import lt.paulius.pentathloncompetition.entity.*;
import lt.paulius.pentathloncompetition.service.api.AthletesService;
import lt.paulius.pentathloncompetition.service.api.FileReader;
import lt.paulius.pentathloncompetition.service.api.ScoreCalculator;
import lt.paulius.pentathloncompetition.utils.DateTimeUtils;
import lt.paulius.pentathloncompetition.utils.FinalScoreComparatorAsc;
import lt.paulius.pentathloncompetition.utils.TotalScoreComparatorDesc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Data
public class AthletesServiceImpl implements AthletesService {

    private FileReader fileReader;

    private List<Athlete> athletes;

    private ScoreCalculator fencingScoreCalculator;

    private ScoreCalculator swimmingScoreCalculator;

    private ScoreCalculator ridingScoreCalculator;

    private ScoreCalculator shootingScoreCalculator;

    public static final int POINTS_DIFFERENCE = 3;

    public static final int SECONDS_GAP = 3;

    @Autowired
    public AthletesServiceImpl(FileReader fileReader) {
        this.fileReader = fileReader;
    }

    @PostConstruct
    public void init(){
        List<List<String>> fileContent = fileReader.readFile(PentathlonCompetitionApplication.FILE_PATH);
        this.athletes = getAthletes(fileContent);

        if (this.athletes != null) {
            fencingScoreCalculator = new FencingScoreCalculator(athletes.size());
            swimmingScoreCalculator = new SwimmingScoreCalculator();
            ridingScoreCalculator = new RidingScoreCalculator();
            shootingScoreCalculator = new ShootingScoreCalculator();
        }
    }

    @Override
    public List<Athlete> getAthletes(List<List<String>> fileContent) {
        if (!validFileContent(fileContent)) {
            return null;
        }

        List<Athlete> athletes = new ArrayList<>();

        for (List<String> row : fileContent) {
            String athleteName = row.get(0);
            String fencingVictories = row.get(1);
            String swimmingTime = row.get(2);
            String ridingKnockingDown = row.get(3);
            String ridingRefusal = row.get(4);
            String ridingDisobedienceLeading = row.get(5);
            String shootingTargetScore = row.get(6);
            String runTime = row.get(7);

            Fencing fencing = new Fencing(Integer.valueOf(fencingVictories));
            Swimming swimming = new Swimming(swimmingTime);
            Riding riding = new Riding(Integer.valueOf(ridingKnockingDown), Integer.valueOf(ridingRefusal),
                    Integer.valueOf(ridingDisobedienceLeading));
            Shooting shooting = new Shooting(Integer.valueOf(shootingTargetScore));
            Run run = new Run(runTime);

            Athlete athlete = new Athlete(athleteName, fencing, swimming, riding, shooting, run);

            athletes.add(athlete);
        }

        return athletes;
    }

    /**
     * Facade structural design pattern.
     *
     * It is used to hide the complexity of the results calculation.
     * First, it sets the total score of athletes after first 4 events.
     * Then, sorts the athletes by total score descending.
     * Then, calculates and sets the concluding event time.
     * Then, calculates and sets the final score.
     * Then, sorts the athletes by the final score ascending.
     * Then, sets the final place of every athlete.
     *
     * @return the list of athletes with calculated results
     */
    @Override
    public List<Athlete> calculateResults() {
        if (athletes == null) {
            return null;
        }

        setTotalScore();

        athletes.sort(new TotalScoreComparatorDesc());

        setConcludingEventTime();

        setFinalScore();

        athletes.sort(new FinalScoreComparatorAsc());

        setPlaces();

        return athletes;
    }

    private boolean validFileContent(List<List<String>> fileContent) {
        if (fileContent == null) {
            return false;
        }

        // TODO: Add more validation checks

        return true;
    }

    private void setTotalScore() {
        for (Athlete athlete : athletes) {
            int totalScore = 0;

            totalScore += fencingScoreCalculator.calculateScore(athlete);
            totalScore += swimmingScoreCalculator.calculateScore(athlete);
            totalScore += ridingScoreCalculator.calculateScore(athlete);
            totalScore += shootingScoreCalculator.calculateScore(athlete);

            athlete.setTotalScore(totalScore);
        }
    }

    private void setConcludingEventTime() {
        if (athletes.size() > 0) {
            Athlete currentLeader = athletes.get(0);
            currentLeader.setConcludingEventTime("00:00.0");
            int leaderPoints = currentLeader.getTotalScore();

            for (int i = 1; i < athletes.size(); i++) {
                Athlete athlete = athletes.get(i);
                int pointsDiff = leaderPoints - athlete.getTotalScore();

                if (pointsDiff < 0) {
                    throw new IllegalStateException("Athlete scores are not sorted!");
                }

                SimpleDateFormat sdf = new SimpleDateFormat(PentathlonCompetitionApplication.TIME_FORMAT);

                try {
                    Date start = sdf.parse("00:00.0");
                    int secondsToAdd = pointsDiff / POINTS_DIFFERENCE * SECONDS_GAP;

                    start = DateTimeUtils.addSeconds(start, secondsToAdd);
                    String concludingEventTime = DateTimeUtils.convertDateToString(start,
                            PentathlonCompetitionApplication.TIME_FORMAT);
                    athletes.get(i).setConcludingEventTime(concludingEventTime);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void setFinalScore() {
        for (Athlete athlete : athletes) {
            if (athlete.getRun() == null
                    || athlete.getRun().getTime() == null
                    || athlete.getConcludingEventTime() == null) {
                continue;
            }

            String concludingEventTime = athlete.getConcludingEventTime();
            String runTime = athlete.getRun().getTime();
            String finalScoreTime = DateTimeUtils.addDates(concludingEventTime, runTime);

            athlete.setFinalScoreTime(finalScoreTime);
        }
    }

    private void setPlaces() {
        int firstIndex = 0, lastIndex = 0;

        for (int i = 0; i < athletes.size(); i++) {
            if (i != athletes.size() - 1
                    && athletes.get(i).getFinalScoreTime().equals(athletes.get(i + 1).getFinalScoreTime())) {
                lastIndex = i + 1;
            } else {
                for (int j = firstIndex; j <= lastIndex; j++) {
                    if (firstIndex != lastIndex) {
                        String place = (firstIndex + 1) + "-" + (lastIndex + 1);
                        athletes.get(j).setPlace(place);
                    } else {
                        String place = String.valueOf(firstIndex + 1);
                        athletes.get(j).setPlace(place);
                    }
                }
                firstIndex = lastIndex = i + 1;
            }
        }
    }
}
