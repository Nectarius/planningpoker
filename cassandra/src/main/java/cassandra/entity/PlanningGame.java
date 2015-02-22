package cassandra.entity;

import org.springframework.data.annotation.Transient;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import java.util.List;

@Table
public class PlanningGame {

    @PrimaryKey
    private int id;

    private String name;

    private String description;

    private List<Integer> participants;

    /* private List<Story> stories;

    private List<Account> participants;*/

    public int  getId() {
        return id;
    }

    public void setId(int  id) {
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

   /*

    public void setStories(List<Story> stories) {
        this.stories = stories;
    }

    public List<Story> getStories() {
        return stories;
    }
*/
    public void setParticipants(List<Integer> participants) {
        this.participants = participants;
    }

    public List<Integer> getParticipants() {
        return participants;
    }


}
