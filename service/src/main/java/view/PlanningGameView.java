package view;

import com.orientechnologies.orient.core.id.ORID;
import entity.Story;

import javax.persistence.OneToMany;
import java.util.List;

/**
 *  data transfer object for Planning Game entity
 */
public class PlanningGameView {

    private String id;

    private String name;

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
