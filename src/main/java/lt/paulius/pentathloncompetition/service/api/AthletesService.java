package lt.paulius.pentathloncompetition.service.api;

import lt.paulius.pentathloncompetition.entity.Athlete;

import java.util.List;

public interface AthletesService {

    List<Athlete> getAthletes(List<List<String>> fileContent);

    List<Athlete> calculateResults();
}
