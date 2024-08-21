package org.example.springjpahr.repositories;

import org.example.springjpahr.entity.Department;
import org.example.springjpahr.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface DepRepo extends JpaRepository<Department,Long> {

}
