package org.example.springjpahr.service;

import org.example.springjpahr.entity.Role;
import org.example.springjpahr.entity.User;
import org.example.springjpahr.repositories.RoleRepo;
import org.example.springjpahr.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepo roleRepo;

    public Role findById(Long id) {
        return roleRepo.findById(id).orElseThrow();
    }

    public Role insert(Role role) {
        return roleRepo.save(role);
    }

    public Role update(Role role) {
        Role cur = roleRepo.findById(role.getId()).orElseThrow();
        cur.setName(role.getName());
        return roleRepo.save(cur);
    }
    public List<Role> findAll() {
        return roleRepo.findAll();
    }

}
