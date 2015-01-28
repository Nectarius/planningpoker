package planninggame;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.PlanningGameService;
import view.PlanningGameView;

import java.util.List;

/**
 * Created by adelfiri on 12/25/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/service-context.xml"})
public class PlanningGameTest {

    @Autowired
    private PlanningGameService planningGameService;

    @Test
    public void savingTest() {
        for (int i = 0; i < 77; i++) {
            PlanningGameView planningGameView1 = new PlanningGameView();
            planningGameView1.setName("Yet no one else" + i);
            planningGameService.createNewPlanningGame(planningGameView1);
        }

    }

    @Test
    public void findAllTest(){
       List<PlanningGameView> planningGameViews = planningGameService.findAllPlanningGames();
        for(PlanningGameView planningGameView : planningGameViews) {
            System.out.println(planningGameView.getName());
        }
    }

}
