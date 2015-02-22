package cassandra.entity;

import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import java.util.List;

@Table
public class Account {

    @PrimaryKey
    private String id;

    private String login;

    private String password;

    private List<String> roles;

    private List<String> scopes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setScopes(List<String> scopes) {
        this.scopes = scopes;
    }

    public List<String> getScopes() {
        return scopes;
    }


}
