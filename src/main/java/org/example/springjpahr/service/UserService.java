package org.example.springjpahr.service;

import org.example.springjpahr.entity.User;
import org.example.springjpahr.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

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

}
