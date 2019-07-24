package lt.paulius.pentathloncompetition.rest;

import lt.paulius.pentathloncompetition.entity.Athlete;
import lt.paulius.pentathloncompetition.service.api.AthletesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PenthathlonResultsController {

    private AthletesService athletesService;

    @Autowired
    public PenthathlonResultsController(AthletesService athletesService) {
        this.athletesService = athletesService;
    }

    @GetMapping(value = "/leaderboard", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Athlete> getLeaderboard() {
        return athletesService.calculateResults();
    }
}
