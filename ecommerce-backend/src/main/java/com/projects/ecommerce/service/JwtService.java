package com.projects.ecommerce.service;


import com.projects.ecommerce.dao.UserDao;
import com.projects.ecommerce.entity.JwtRequest;
import com.projects.ecommerce.entity.JwtResponse;
import com.projects.ecommerce.entity.Users;
import com.projects.ecommerce.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class JwtService  implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {
        String userName = jwtRequest.getUserName();
        String userPassWord = jwtRequest.getUserPassWord();
        authenticate(userName, userPassWord);
        final UserDetails userDetails = loadUserByUsername(userName);
        String jwtToken = jwtUtil.generateToken(userDetails);
        Users users = userDao.findById(userName).get();
        JwtResponse jwtResponse = new JwtResponse(users, jwtToken);
        return jwtResponse;


    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = userDao.findById(username).get();
        if(users != null){
            return  new User(users.getUserName(), users.getPassword(),getAuthorities(users));
        }
        else{
            throw  new UsernameNotFoundException("UserName is not valid..");
        }
    }

    private  void  authenticate(String userName, String userPassWord) throws Exception {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, userPassWord));
        }
        catch (DisabledException  e){
            throw new Exception("User is Disabled");
        }
        catch (BadCredentialsException e){
            throw  new Exception("Bad credential");
        }
    }
    private Set getAuthorities(Users users){
        Set authorities = new HashSet();
        users.getRole().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        });
        return authorities;
    }
}
