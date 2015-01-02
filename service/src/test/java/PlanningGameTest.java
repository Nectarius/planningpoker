import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.PlanningGameService;
import view.PlanningGameView;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by adelfiri on 12/25/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/service-context.xml"})
public class PlanningGameTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private PlanningGameService planningGameService;

    @Test
    public void savingTest() {
        PlanningGameView planningGameView1 = new PlanningGameView();
        planningGameView1.setName("Yet no one else");
        planningGameService.createNewPlanningGame(planningGameView1);
    }

    @Test
    public void findAllTest(){
       List<PlanningGameView> planningGameViews = planningGameService.findAllPlanningGames();
        for(PlanningGameView planningGameView : planningGameViews) {
            System.out.println(planningGameView.getName());
        }
    }

}
