package org.example.springjpahr.service;

import org.example.springjpahr.entity.Employee;
import org.example.springjpahr.repositories.EmpRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpService {

    @Autowired
    private EmpRepo empRepo;
    @Autowired
    private DepService depService;

    public Employee findById(Long id) {
        return empRepo.findById(id).orElseThrow();
    }

    public List<Employee> filter(String name) {
        return empRepo.filterNative(name);
    }

    public Employee insert(Employee emp) {
        if (emp.getDepartment()!=null& emp.getDepartment().getId()!=null){
            emp.setDepartment(depService.findById(emp.getDepartment().getId()));
        }
        return empRepo.save(emp);
    }
    public Employee update(Employee emp) {
        Employee cur = empRepo.findById(emp.getId()).orElseThrow();
        cur.setName(emp.getName());
        cur.setSalary(emp.getSalary());
        cur.setDepartment(emp.getDepartment());
        return empRepo.save(cur);
    }

    public List<Employee> findAll() {
        return empRepo.findAll();
    }

    public List<Employee> findByDepartmentId(Long deptId) {
        return empRepo.findByDepartment(deptId);
    }
}
