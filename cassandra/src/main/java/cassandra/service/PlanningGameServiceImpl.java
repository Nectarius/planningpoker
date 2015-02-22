package cassandra.service;


import cassandra.entity.PlanningGame;
import cassandra.mapper.PlanningGameMapper;
import cassandra.repository.PlanningGameRepository;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.stereotype.Service;
import service.PlanningGameService;
import view.PagedListView;
import view.PlanningGameView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 */
@Service
public class PlanningGameServiceImpl implements PlanningGameService {

    @Autowired
    private PlanningGameMapper mapper;

    @Autowired
    @Qualifier("cassandraTemplate")
    private CassandraOperations cassandraOperations;

    @Autowired
    private PlanningGameRepository planningGameRepository;

    @Override
    public List<PlanningGameView> findAllPlanningGames() {

        Iterable<PlanningGame> planningGames = planningGameRepository.findAll();
        return mapper.planningGameToPlanningGameViewDtos(planningGames);
    }

    @Override
    public String createNewPlanningGame(PlanningGameView planningGameView) {
        PlanningGame planningGame = mapper.planningGameViewToPlanningGame(planningGameView);
        planningGame = cassandraOperations.insert(planningGame);
        return String.valueOf(planningGame.getId());
    }

    @Override
    public void updatePlanningGame(PlanningGameView planningGameView) {

    }

    @Override
    public PlanningGameView loadPlanningGame(String id) {
        Select select = QueryBuilder.select().from("planninggame");
        select.where(QueryBuilder.eq("id", Integer.parseInt(id)));
        PlanningGame planningGame = cassandraOperations.selectOne(select, PlanningGame.class);
        return mapper.planningGameToPlanningGameView(planningGame);
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public PagedListView<PlanningGameView> findAll(Integer pageNumber, Integer pageSize) {
        Integer limit = pageSize*pageNumber -1;
        Integer skip = pageSize * (pageNumber-1);
        Iterable<PlanningGame> planningGames = planningGameRepository.findAll();
        List<PlanningGame> outcome = new ArrayList<PlanningGame>();
        Iterator<PlanningGame> iterator = planningGames.iterator();
        int i = 0;
        while (iterator.hasNext()) {

            PlanningGame next = iterator.next();
            if (i >= skip && i <= limit) {
                outcome.add(next);
            }
            i++;
        }
        List<PlanningGameView> planningGameViewList = mapper.planningGameToPlanningGameViewDtos(outcome);
        PagedListView pagedListView = new PagedListView();
        pagedListView.setEntities(planningGameViewList);
        pagedListView.setPageNum(pageNumber);
        pagedListView.setPageSize(pageSize);
        pagedListView.setTotal(i);
        return pagedListView;
    }
}
