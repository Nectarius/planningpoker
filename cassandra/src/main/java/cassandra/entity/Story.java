package cassandra.entity;


import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import java.util.List;

@Table
public class Story {

    @PrimaryKey
    private String id;

    private String name;

    private String description;

   // private List<Task> tasks;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

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

   // public void setTasks(List<Task> tasks) {
    //    this.tasks = tasks;
   // }

    //public List<Task> getTasks() {
    //    return tasks;
   // }

}
