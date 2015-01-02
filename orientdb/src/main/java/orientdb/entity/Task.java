package orientdb.entity;

import com.orientechnologies.orient.core.id.ORID;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Task {

    @Id
    private ORID id;

    private String description;

    private String name;

    private Integer estimate;

    @ElementCollection
    private List<String> tags;

    private String scope;

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
