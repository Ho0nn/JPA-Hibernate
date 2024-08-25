package org.example.springjpahr.repositories;

import jakarta.transaction.Transactional;
import org.example.springjpahr.HRStatisticProjection;
import org.example.springjpahr.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface EmpRepo extends JpaRepository<Employee, Long> {

    List<Employee> findByDepartment(Long deptId);
    List<Employee>findBySalaryAndName(Double salary,String name);
    List<Employee> findByNameAndDepartmentName(String empName, String deptName);
    Long  countByNameAndDepartmentName(String empName, String deptName);
    @Modifying
    Long  deleteByNameAndDepartmentName(String empName, String deptName);


      //JPQL using sorting
//    @Query("select emp from Employee emp where (:empName is null or emp.name like :empName)")
//    List<Employee> filter(@Param("empName") String name, Sort sort);

    //JPQL using pagable
//      @Query("select emp from Employee emp where (:empName is null or emp.name like :empName)")
//      Page<EmpProjection> filter(@Param("empName") String name, Pageable pageable);

    //JPQL using Projection-based-constructor
    @Query("select new Employee(emp.id,emp.name) from Employee emp where (:empName is null or emp.name like :empName)")
    Page<Employee> filter(@Param("empName") String name, Pageable pageable);



    //SQL Native
    @Query(value = "select * from employees emp where emp.name like :empName", nativeQuery = true)
    List<Employee> filterNative(@Param("empName") String name);

    @Query(value = "select (select count(*) from departments) deptCnt,"+
            "(select count(*) from employee )empCnt,"+
            "(select count(*) from user) userCnt"
            ,nativeQuery = true)
       HRStatisticProjection getHRStatistic();

//    @Query("select emp from Employee emp join emp.department dept where dept.id = :deptId")
//    List<Employee> findByDepartment(@Param("deptId") Long deptId);
}
