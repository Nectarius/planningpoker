package cassandra.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import cassandra.entity.PlanningGame;
import view.PlanningGameView;

import java.util.List;

/**
 * Created by adelfiri on 12/26/14.
 */
@Mapper(componentModel = "spring")
public interface PlanningGameMapper {


    List<PlanningGameView> planningGameToPlanningGameViewDtos(Iterable<PlanningGame> cars);

   @Mapping(target = "id",
            expression = "java(  String.valueOf(planningGame.getId()) )")
    PlanningGameView planningGameToPlanningGameView(PlanningGame planningGame);

    @Mappings({
   // @Mapping(target = "id",
   //         ignore = true)
   // @Mapping(target = "stories",
   //         ignore = true),
    @Mapping(target = "participants",
            ignore = true)
     })
    PlanningGame planningGameViewToPlanningGame(PlanningGameView planningGameView);
}
