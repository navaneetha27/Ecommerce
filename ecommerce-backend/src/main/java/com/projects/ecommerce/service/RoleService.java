package com.projects.ecommerce.service;


import com.projects.ecommerce.dao.RoleDao;
import com.projects.ecommerce.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    private RoleDao roleDao;
    public Role createRole(Role role){
        return roleDao.save(role);
    }
}
