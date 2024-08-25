package org.example.springjpahr.controller;
import org.example.springjpahr.entity.EmpResponse;
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
    public EmpResponse findById(@PathVariable Long id) {
        Employee emp = empService.findById(id);
        EmpResponse res = new EmpResponse();
        res.setId(emp.getId());
        res.setName(emp.getName());
        res.setSalary(emp.getSalary());
        res.setDepartment(emp.getDepartment());
        res.setUser(emp.getUser());
        return res;
    }

    @GetMapping("/emp-dept")
    public List<Employee> findByEmpAndDept(@RequestParam String empName,@RequestParam String deptName){
        return empService.findByEmpAndDept(empName,deptName);
    }
    @GetMapping("/count-emp-dept")
    public ResponseEntity<Long> countEmpDept(@RequestParam String empName,@RequestParam String deptName){
        return ResponseEntity.ok(empService.countByEmpAndDept(empName,deptName));
    }
    @DeleteMapping("/delete-emp-dept")
    public ResponseEntity<Long> deleteEmpDept(@RequestParam String empName,@RequestParam String deptName){
        return ResponseEntity.ok(empService.deleteEmpDept(empName,deptName));
    }

    @GetMapping("/filter")
    public ResponseEntity<?> findByName(@RequestParam String name,@RequestParam int pageNum,@RequestParam  int pageSize
            ,@RequestParam String sortCol,@RequestParam Boolean isAsc ) {
     //   List<Employee> employees = empService.filter(name,pageNum,pageSize,sortCol,isAsc);
        return ResponseEntity.ok(empService.filter(name,pageNum,pageSize,sortCol,isAsc));
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
    @GetMapping("/salary")
    public ResponseEntity<?> findBySalaryAndName(@RequestParam Double salary, @RequestParam String name){
        return ResponseEntity.ok(empService.findBySalaryAndName(salary,name));
    }
    @GetMapping("satistic")
    public ResponseEntity<?> getHRStatistic(){
        return ResponseEntity.ok(empService.getHRStatistic());
    }
}
