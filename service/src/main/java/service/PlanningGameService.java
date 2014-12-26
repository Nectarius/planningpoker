package service;

import com.orientechnologies.orient.core.id.ORID;
import view.PlanningGameView;

import java.util.List;

/**
 *
 */
public interface PlanningGameService {

    List<PlanningGameView> findAllPlanningGames();

    void createNewPlanningGame(PlanningGameView planningGameView);

    void updatePlanningGame(PlanningGameView planningGameView);

    PlanningGameView loadPlanningGame(String id);

}
