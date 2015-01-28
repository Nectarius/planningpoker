package service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import view.PlanningGameView;

import java.util.List;

/**
 * Created by adelfiri on 1/28/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/application-context.xml"})
public class PlanningGameServiceTest {

    @Autowired
    private PlanningGameService planningGameService;

    @Test
    public void findAllTest(){

        List<PlanningGameView> planningGameViews = planningGameService.findAll(1, 10).getEntities();

        for (PlanningGameView planningGameView : planningGameViews) {
            System.out.println("planningGameView.getName() = " + planningGameView.getName());
        }

    }

}
