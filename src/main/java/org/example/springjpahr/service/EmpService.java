package org.example.springjpahr.service;

import org.example.springjpahr.HRStatisticProjection;
import org.example.springjpahr.Projection.EmpProjection;
import org.example.springjpahr.entity.Employee;
import org.example.springjpahr.repositories.EmpRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

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

    public List<Employee> findByEmpAndDept(String empName, String deptName){
        return empRepo.findByNameAndDepartmentName(empName, deptName);
    }
    public Long countByEmpAndDept(String empName, String deptName){
        return empRepo.countByNameAndDepartmentName(empName, deptName);
    }
    public Long  deleteEmpDept (String empName, String deptName){
        return empRepo.deleteByNameAndDepartmentName(empName, deptName);
    }

    public Page<Employee> filter(String name, int pageNum, int pageSize, String sortCol, Boolean isAsc) {
       if(name.isEmpty()|| name.isBlank()||name==null){
           name=null;
       }
        Pageable pageable = PageRequest.of(pageNum,pageSize,Sort.by(isAsc? Sort.Direction.ASC: Sort.Direction.DESC,sortCol));
       return empRepo.filter(name,pageable);
       // return empRepo.filter(name, Sort.by(isAsc? Sort.Direction.ASC: Sort.Direction.DESC,sortCol));
    }

    public Employee insert(Employee emp) {
        if (emp.getDepartment() != null && emp.getDepartment().getId() != null) {
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

    public List<Employee>findBySalaryAndName(@RequestParam Double salary,@RequestParam String name){
        return empRepo.findBySalaryAndName(salary,name);
    }
    public HRStatisticProjection getHRStatistic(){
        return empRepo.getHRStatistic();
    }
}
