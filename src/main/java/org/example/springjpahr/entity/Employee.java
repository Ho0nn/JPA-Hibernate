package org.example.springjpahr.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@NamedQuery(name = "Employee.findBySalaryAndName",query = "select emp from Employee emp where emp.salary>=:salary and name like : name")

//Mapping 
@SqlResultSetMapping(
        name="empMapping",
        entities = @EntityResult(
        entityClass = Employee.class,
        fields={
                @FieldResult(name = "id",column = "id"),
                @FieldResult(name = "name",column = "name"),
                @FieldResult(name = "salary",column = "salary")}))

@NamedNativeQuery(name = "Employee.findByDepartment",query = "select * from employee where department_id=:deptId",resultSetMapping = "empMapping")


@SequenceGenerator(name = "employee_gen", sequenceName = "employee_seq", initialValue = 100)
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_gen")
    private Long id;

    private String name;
    private Double salary;
    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Employee() {}


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

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
