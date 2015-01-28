package controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.PlanningGameService;
import view.PagedListView;
import view.PlanningGameListView;
import view.PlanningGameView;

import java.util.ArrayList;
import java.util.Arrays;

@RestController
public class PGRestController {

    @Autowired
    private PlanningGameService planningGameService;

    @RequestMapping(value = "games", method = RequestMethod.GET)
    public PagedListView<PlanningGameView> getPlanningGameList(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        return planningGameService.findAll(pageNumber, pageSize);
    }

    @RequestMapping(value = "games", method = RequestMethod.POST)
    public void createPlanningGame(@RequestBody PlanningGameView game) {
        planningGameService.createNewPlanningGame(game);
    }

}
