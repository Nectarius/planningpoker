package view;

import entity.Story;

import java.util.List;

/**
 *  data transfer object for Planning Game entity
 */
public class PlanningGameView {

    private List<Story> stories;

    private String theme;

    private String description;

    public List<Story> getStories() {
        return stories;
    }

    public void setStories(List<Story> stories) {
        this.stories = stories;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
