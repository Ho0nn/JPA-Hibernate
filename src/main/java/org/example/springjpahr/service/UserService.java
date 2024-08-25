package org.example.springjpahr.service;

import org.example.springjpahr.entity.Role;
import org.example.springjpahr.entity.User;
import org.example.springjpahr.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;
    private RoleService roleService;

    public User findById(Long id) {
        return userRepo.findById(id).orElseThrow();
    }

    public User insert(User user) {
        return userRepo.save(user);
    }

    public User update(User user) {
        User cur = userRepo.findById(user.getId()).orElseThrow();
        cur.setUserName(user.getUserName());
        cur.setPassword(user.getPassword());
        return userRepo.save(cur);
    }
    public List<User> findAll() {
        return userRepo.findAll();
    }
    //commit/rollback
    @Transactional
    public void addRole(String roleName) {
        Role role = roleService.findByName(roleName);
       findAll().forEach(user->{
           user.addRole(role);
           userRepo.save(user);
       });//end transaction
    }

}
