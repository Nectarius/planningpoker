package orientdb.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import orientdb.entity.PlanningGame;
import view.PlanningGameView;

import java.util.List;

/**
 * Created by adelfiri on 12/26/14.
 */
@Mapper(componentModel = "spring")
public interface PlanningGameMapper {


    List<PlanningGameView> planningGameToPlanningGameViewDtos(List<PlanningGame> cars);

    @Mapping(target = "id",
            expression = "java( new String( planningGame.getId().getIdentity().toString() ) )")
    PlanningGameView planningGameToPlanningGameView(PlanningGame planningGame);

    @Mappings({
    @Mapping(target = "id",
            ignore = true),
    @Mapping(target = "stories",
            ignore = true),
    @Mapping(target = "participants",
            ignore = true)
     })
    PlanningGame planningGameViewToPlanningGame(PlanningGameView planningGameView);
}
