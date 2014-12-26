package service.impl;

import com.orientechnologies.orient.core.query.OQuery;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;
import com.orientechnologies.orient.object.iterator.OObjectIteratorClass;
import entity.PlanningGame;
import mapper.PlanningGameMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.PlanningGameService;
import view.PlanningGameView;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Service
@Transactional
public class PlanningGameServiceImpl implements PlanningGameService {

    @Autowired
    private PlanningGameMapper planningGameMapper;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<PlanningGameView> findAllPlanningGames() {
        OObjectDatabaseTx databaseTx = (OObjectDatabaseTx) entityManager.getDelegate();
        OObjectIteratorClass<PlanningGame> planningGames = databaseTx.browseClass(PlanningGame.class);
        return planningGameMapper.createList(planningGames);
    }

    @Override
    public void createNewPlanningGame(PlanningGameView planningGameView) {
        PlanningGame planningGame = planningGameMapper.copyFrom(planningGameView, new PlanningGame());
        entityManager.persist(planningGame);
    }

    @Override
    public void updatePlanningGame(PlanningGameView planningGameView) {
        PlanningGame planningGame = entityManager.find(PlanningGame.class,  planningGameView.getId());
        planningGame = planningGameMapper.copyFrom(planningGameView, planningGame);
        entityManager.persist(planningGame);
    }

    @Override
    public PlanningGameView loadPlanningGame(String id) {
        PlanningGame planningGame = entityManager.find(PlanningGame.class,  id);
        return planningGameMapper.create(planningGame);
    }

  /*  private PlanningGameView createPlanningGameStub(String name, String description){

        PlanningGameView planningGameView = new PlanningGameView();

        planningGameView.setName(name);

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
    }*/



}
