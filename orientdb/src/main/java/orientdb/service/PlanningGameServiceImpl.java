package orientdb.service;

import com.orientechnologies.orient.object.iterator.OObjectIteratorClass;
import org.apache.commons.collections.IteratorUtils;
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
    private PlanningGameMapper mapper;

    @Autowired
    private OrientDBEntityManager orientDBEntityManager;

    @Override
    public List<PlanningGameView> findAllPlanningGames() {

        OObjectIteratorClass<PlanningGame> planningGames = orientDBEntityManager.browseClass(PlanningGame.class);

        return mapper.planningGameToPlanningGameViewDtos(IteratorUtils.toList(planningGames));

    }

    @Override
    public String createNewPlanningGame(PlanningGameView planningGameView) {
        PlanningGame planningGame = mapper.planningGameViewToPlanningGame(planningGameView);
        return orientDBEntityManager.persist(planningGame).getId().toString();
    }

    @Override
    public void updatePlanningGame(PlanningGameView planningGameView) {
        //PlanningGame planningGame = orientDBEntityManager.find(PlanningGame.class, planningGameView.getId());
        //mapper.map(planningGameView, planningGame);
        //orientDBEntityManager.persist(planningGame);
    }

    @Override
    public PlanningGameView loadPlanningGame(String id) {
       PlanningGame planningGame = orientDBEntityManager.find(PlanningGame.class, id);
       return mapper.planningGameToPlanningGameView(planningGame);
    }

    @Override
    public void delete(String id) {
        orientDBEntityManager.delete(id);
    }

    public void deleteAll(){
        throw new UnsupportedOperationException("deleteAll");
    }

}
