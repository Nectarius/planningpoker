package service;

import view.PlanningGameView;

import java.util.List;

/**
 *
 */
public interface PlanningGameService {

    List<PlanningGameView> findAllPlanningGames();

    String createNewPlanningGame(PlanningGameView planningGameView);

    void updatePlanningGame(PlanningGameView planningGameView);

    PlanningGameView loadPlanningGame(String id);

    void delete(String id);

    void deleteAll();

}
