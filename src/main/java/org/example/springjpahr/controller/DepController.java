package org.example.springjpahr.controller;

import org.example.springjpahr.entity.Department;
import org.example.springjpahr.entity.Employee;
import org.example.springjpahr.service.DepService;
import org.example.springjpahr.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepController {
    @Autowired
    private DepService depService;
    @GetMapping("/{id}")
    public Department findById(@PathVariable Long id) {
        return depService.findById(id);
    }
    @PostMapping()
    public Department insert(@RequestBody Department dep) {
        return depService.insert(dep);
    }
    @PutMapping()
    public Department update(@RequestBody Department dep) {
        return depService.update(dep);
    }
    @GetMapping()
    public List<Department> findAll() {

        return depService.findAll();
    }
}
