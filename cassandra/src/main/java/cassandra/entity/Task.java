package cassandra.entity;


import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import java.util.List;

@Table
public class Task {

    @PrimaryKey
    private String id;

    private String description;

    private String name;

    private Integer estimate;

    private List<String> tags;

    private String scope;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setEstimate(Integer estimate) {
        this.estimate = estimate;
    }

    public Integer getEstimate() {
        return estimate;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getScope() {
        return scope;
    }
}
