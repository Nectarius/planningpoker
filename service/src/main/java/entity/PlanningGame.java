package entity;

import com.orientechnologies.orient.core.id.ORID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import java.util.List;

@Entity
public class PlanningGame {

    @Id
    private ORID id;

    @Column(name = "name")
    private String name;

    private String description;

    @OneToMany
    private List<Story> stories;

    @OneToMany
    private List<Account> participants;

    public ORID getId() {
        return id;
    }

    public void setId(ORID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setStories(List<Story> stories) {
        this.stories = stories;
    }

    public List<Story> getStories() {
        return stories;
    }

    public void setParticipants(List<Account> participants) {
        this.participants = participants;
    }

    public List<Account> getParticipants() {
        return participants;
    }


}
