package org.example.springjpahr.service;

import org.example.springjpahr.entity.Department;
import org.example.springjpahr.entity.Employee;
import org.example.springjpahr.repositories.DepRepo;
import org.example.springjpahr.repositories.EmpRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepService {
    @Autowired
    private DepRepo depRepo;
    public Department findById(Long id) {
        return depRepo.findById(id).orElseThrow();
    }
    public Department insert(Department dep) {
        return depRepo.save(dep);
    }
    public Department update(Department dep) {
        Department cur = depRepo.findById(dep.getId()).get();
        cur.setName(dep.getName());
        return depRepo.save(dep);
    }
    public List<Department> findAll(){

        return depRepo.findAll();
    }

}
