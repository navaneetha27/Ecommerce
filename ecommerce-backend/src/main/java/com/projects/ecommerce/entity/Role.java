package com.projects.ecommerce.entity;

import org.springframework.stereotype.Component;
import jakarta.persistence.*;



@Entity
@Table(name = "Role",schema = "ecommerce")
public class Role {
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    @Id
    private String roleName;
    private String  roleDescription;
}
