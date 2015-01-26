package controller.rest;

import org.springframework.web.bind.annotation.*;
import view.PlanningGameListView;
import view.PlanningGameView;

import java.util.ArrayList;
import java.util.Arrays;

@RestController
public class PGRestController {
    private static PlanningGameListView games = getStubData();

    private static PlanningGameListView getStubData() {
        PlanningGameListView data = new PlanningGameListView();
        data.setEntities(new ArrayList<PlanningGameView>());
        data.getEntities().add(new PlanningGameView("55", "Sprint 1", "Test game 1"));
        data.getEntities().add(new PlanningGameView("2", "Sprint 2", "Test game 2"));
        data.getEntities().add(new PlanningGameView("3", "Sprint 3", "Test game 3"));
        data.setTotal(data.getEntities().size());
        return data;
    }

    @RequestMapping(value = "games", method = RequestMethod.GET)
    public PlanningGameListView getPlanningGameList() {
        return games;
    }

    @RequestMapping(value = "games", method = RequestMethod.POST)
    public void createPlanningGame(@RequestBody PlanningGameView game) {
        games.getEntities().add(game);
        games.setTotal(games.getTotal() + 1);
    }
}
