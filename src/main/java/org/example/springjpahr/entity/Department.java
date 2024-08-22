package org.example.springjpahr.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "department_gen")
    @TableGenerator(name = "department_gen", table = "department_seq", allocationSize = 1)
    private Long id;

    private String name;

//    @OneToMany(mappedBy = "department",fetch = FetchType.LAZY)
//    @JsonIgnore
//    private List<Employee> employees;

    // getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public List<Employee> getEmployees() {
//        return employees;
//    }
//
//    public void setEmployees(List<Employee> employees) {
//        this.employees = employees;
//    }
}
