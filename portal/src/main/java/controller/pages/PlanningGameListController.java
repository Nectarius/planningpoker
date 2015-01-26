package controller.pages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 *
 */
@Controller
public class PlanningGameListController {

    //private final static Logger LOGGER = LoggerFactory.getLogger(PlanningGameListController.class.getName());

    @RequestMapping(value = "planninggamelist", method = RequestMethod.GET)
    public String getEventPage() {
        return "planninggamelist";
    }

}
