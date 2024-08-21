package org.example.springjpahr.controller;

import org.example.springjpahr.entity.Employee;
import org.example.springjpahr.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmpController {

    @Autowired
    private EmpService empService;

    @GetMapping("/{id}")
    public ResponseEntity<Employee> findById(@PathVariable Long id) {
        Employee employee = empService.findById(id);
        return ResponseEntity.ok(employee);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Employee>> findByName(@RequestParam String name) {
        List<Employee> employees = empService.filter(name);
        return ResponseEntity.ok(employees);
    }

    @PostMapping
    public ResponseEntity<Long> insert(@RequestBody Employee emp) {
        Employee inserted = empService.insert(emp);
        return ResponseEntity.status(HttpStatus.CREATED).body(inserted.getId());
    }

    @PutMapping
    public ResponseEntity<Employee> update(@RequestBody Employee emp) {
        Employee updated = empService.update(emp);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/department/{deptId}")
    public ResponseEntity<List<Employee>> findByDepartmentId(@PathVariable Long deptId) {
        List<Employee> employees = empService.findByDepartmentId(deptId);
        return ResponseEntity.ok(employees);
    }

    @GetMapping
    public ResponseEntity<List<Employee>> findAll() {
        List<Employee> employees = empService.findAll();
        return ResponseEntity.ok(employees);
    }
}
