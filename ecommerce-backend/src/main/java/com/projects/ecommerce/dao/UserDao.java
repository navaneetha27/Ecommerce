package com.projects.ecommerce.dao;


import com.projects.ecommerce.entity.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao  extends CrudRepository<Users, String> {
}
