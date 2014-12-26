import com.orientechnologies.orient.core.tx.OTransaction;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;
import com.orientechnologies.orient.object.jpa.OJPAEntityManager;
import entity.Story;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import service.PlanningGameService;
import view.PlanningGameView;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.io.IOException;
import java.net.URL;
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
        planningGameView1.setName("Wine");
        planningGameService.createNewPlanningGame(planningGameView1);
    }

    @Test
    public void findAllTest(){
       List<PlanningGameView> planningGameViews = planningGameService.findAllPlanningGames();
        for(PlanningGameView planningGameView : planningGameViews) {
            //System.out.println(planningGameView.getName());
        }
    }

}
