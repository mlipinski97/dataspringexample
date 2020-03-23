package pl.lipinski.springdataexample.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    /*@JsonIgnore*/
    //TODO: learn about DTO, shouldnt send password by JSON, learn aabout form base data
    @Column(nullable = false)
    private String password;

    @NotNull
    private boolean isEnabled;

    @NotNull
    private String roles;

    public User() {
    }

    public User(String username, String password, boolean isEnabled, String roles) {
        this.username = username;
        this.password = password;
        this.isEnabled = isEnabled;
        this.roles = roles;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        this.isEnabled = enabled;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
