package orientdb.mapper;

import orientdb.entity.PlanningGame;
import org.springframework.stereotype.Service;
import view.PlanningGameView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adelfiri on 12/26/14.
 */
@Service
public class PlanningGameMapper {

    public PlanningGameView create(PlanningGame planningGame) {
        if (planningGame == null) return null;
        PlanningGameView planningGameView = new PlanningGameView();
        return copyTo(planningGame, planningGameView);
    }

    public List<PlanningGameView> createList(Iterable<PlanningGame> source) {
        List<PlanningGameView> result = new ArrayList<PlanningGameView>();
        for (PlanningGame planningGame : source) {
            result.add(create(planningGame));
        }
        return result;
    }

    public PlanningGameView copyTo(PlanningGame source, PlanningGameView destination) {
        if (source.getId() != null) {
            destination.setId(source.getId().getIdentity().toString());
        }
        destination.setName(source.getName());
        destination.setDescription(source.getDescription());
        return destination;
    }

    public PlanningGame copyFrom(PlanningGameView source, PlanningGame destination) {
        destination.setDescription(source.getDescription());
        destination.setName(source.getName());
        return destination;
    }

}
