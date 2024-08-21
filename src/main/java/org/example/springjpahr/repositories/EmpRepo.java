package org.example.springjpahr.repositories;

import org.example.springjpahr.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpRepo extends JpaRepository<Employee, Long> {

    List<Employee> findByName(String name);

    @Query("select emp from Employee emp where emp.name like :empName")
    List<Employee> filter(@Param("empName") String name);

    @Query(value = "select * from employees emp where emp.name like :empName", nativeQuery = true)
    List<Employee> filterNative(@Param("empName") String name);

    @Query("select emp from Employee emp join emp.department dept where dept.id = :deptId")
    List<Employee> findByDepartment(@Param("deptId") Long deptId);
}
