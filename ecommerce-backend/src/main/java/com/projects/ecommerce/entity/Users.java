package com.projects.ecommerce.entity;



import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(schema = "ecommerce")
public class Users {
    @Id
     private String userName;
    private String firstName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }

    private String lastName;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLE",schema = "ecommerce",joinColumns = {
            @JoinColumn(name = "USER_ID")},inverseJoinColumns = {@JoinColumn(name = "ROLE_ID")})
    private Set<Role> role;
}
