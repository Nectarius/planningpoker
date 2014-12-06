package service.impl;

import org.springframework.stereotype.Service;
import service.PlanningGameService;
import view.PlanningGameView;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Service
public class PlanningGameServiceImpl implements PlanningGameService {

    private PlanningGameView createPlanningGameStub(String theme, String description){

        PlanningGameView planningGameView = new PlanningGameView();

        planningGameView.setTheme(theme);

        planningGameView.setDescription(description);

        return planningGameView;
    }

    private List<PlanningGameView> createPlanningGameListStub(){
        List<PlanningGameView> games = new ArrayList<PlanningGameView>();
        games.add(createPlanningGameStub("test1", "description1"));
        games.add(createPlanningGameStub("test2", "description2"));
        games.add(createPlanningGameStub("test3", "description3"));
        games.add(createPlanningGameStub("test4", "description4"));
        games.add(createPlanningGameStub("test5", "description5"));
        return games;
    }

    @Override
    public List<PlanningGameView> findAllPlanningGames() {
        return createPlanningGameListStub();
    }

}
