package com.projects.ecommerce.service;


import com.projects.ecommerce.dao.RoleDao;
import com.projects.ecommerce.dao.UserDao;
import com.projects.ecommerce.entity.Role;
import com.projects.ecommerce.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {
  @Autowired
  UserDao userDao;

  @Autowired
  private PasswordEncoder passwordEncoder;
  @Autowired
  private RoleDao roleDao;

  public void initRoleAndUser()
  {
    Role adminRole = new Role();
    adminRole.setRoleName("Admin");
    adminRole.setRoleDescription("Admin role");
    roleDao.save(adminRole);

    Role userRole = new Role();
    userRole.setRoleName("User");
    userRole.setRoleDescription("Default role for newly created record");
    roleDao.save(userRole);

    Users adminUser = new Users();
    adminUser.setUserName("admin123");
    adminUser.setPassword(getEncodedPassWord("admin@pass"));
    adminUser.setFirstName("admin");
    adminUser.setLastName("admin");
    Set<Role> adminRoles = new HashSet<>();
    adminRoles.add(adminRole);
    adminUser.setRole(adminRoles);
    userDao.save(adminUser);
  }    public Users registerNewUser(Users user){
    Role role = roleDao.findById("User").get();
    Set<Role> userRoles = new HashSet<>();
    userRoles.add(role);
    user.setRole(userRoles);
    user.setPassword(getEncodedPassWord(user.getPassword()));
      return userDao.save(user);
  }

  public  String getEncodedPassWord(String passWord){
      return  passwordEncoder.encode(passWord);
  }
}
