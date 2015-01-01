package orientdb.service;

import com.orientechnologies.orient.object.iterator.OObjectIteratorClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import orientdb.entity.PlanningGame;
import orientdb.mapper.PlanningGameMapper;
import service.PlanningGameService;
import view.PlanningGameView;

import java.util.List;

/**
 *
 */
@Service
@Transactional
public class PlanningGameServiceImpl implements PlanningGameService {

    @Autowired
    private PlanningGameMapper planningGameMapper;

    @Autowired
    private OrientDBEntityManager orientDBEntityManager;

    @Override
    public List<PlanningGameView> findAllPlanningGames() {
        OObjectIteratorClass<PlanningGame> planningGames = orientDBEntityManager.browseClass(PlanningGame.class);
        return planningGameMapper.createList(planningGames);
    }

    @Override
    public void createNewPlanningGame(PlanningGameView planningGameView) {
        PlanningGame planningGame = planningGameMapper.copyFrom(planningGameView, new PlanningGame());
        orientDBEntityManager.persist(planningGame);
    }

    @Override
    public void updatePlanningGame(PlanningGameView planningGameView) {
        PlanningGame planningGame = orientDBEntityManager.find(PlanningGame.class, planningGameView.getId());
        planningGame = planningGameMapper.copyFrom(planningGameView, planningGame);
        orientDBEntityManager.persist(planningGame);
    }

    @Override
    public PlanningGameView loadPlanningGame(String id) {
        PlanningGame planningGame = orientDBEntityManager.find(PlanningGame.class, id);
        return planningGameMapper.create(planningGame);
    }

}
