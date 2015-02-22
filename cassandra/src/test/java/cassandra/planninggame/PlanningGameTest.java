package cassandra.planninggame;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import service.PlanningGameService;
import view.PagedListView;
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

   /* @Test
    public void savingTest() {

        for(int i=0;i<150;i++) {
            PlanningGameView planningGameView1 = new PlanningGameView();
            planningGameView1.setId(String.valueOf(i));
            planningGameView1.setName("Yet no one else" + i);
            String crystalKey = planningGameService.createNewPlanningGame(planningGameView1);

        }

       // System.out.println("crystalKey = " + crystalKey);
        List<PlanningGameView> list = planningGameService.findAllPlanningGames();

        for (PlanningGameView planningGameView : list) {
            System.out.println("planningGameView.getName() = " + planningGameView.getName());
        }


    }*/

    /*@Test
    public void findOneTest() {

        PlanningGameView planningGameView = planningGameService.loadPlanningGame("55");

        //for (PlanningGameView planningGameView : list) {
            System.out.println("planningGameView.getName() = " + planningGameView.getName());
       // }


    }*/


    /*@Test
    public void view(){
        List<PlanningGameView> list = planningGameService.findAllPlanningGames();

        for (PlanningGameView planningGameView : list) {
            System.out.println("planningGameView.getName() = " + planningGameView.getName());
        }

    }*/

    @Test
    public void findAllTest(){

        System.out.println("///////////////////////////////////////////////////////");
        PagedListView<PlanningGameView> page = planningGameService.findAll(3,9);

        for (PlanningGameView planningGameView :  page.getEntities()) {
            System.out.println("planningGameView.getName() = " + planningGameView.getName());
        }

        System.out.println("///////////////////////////////////////////////////////");

    }

}
