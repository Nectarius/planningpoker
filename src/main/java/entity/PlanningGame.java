package entity;

import java.util.List;

public class PlanningGame {
    private String name;
    private String description;
    private List<Story> stories;
    private List<Account> participants;

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
