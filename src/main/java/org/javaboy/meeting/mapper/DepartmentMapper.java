package org.javaboy.meeting.mapper;

import org.apache.ibatis.annotations.Param;
import org.javaboy.meeting.domain.Department;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface DepartmentMapper {

    List<Department> getAllDeps();

    Department getDepartmentById(Integer departmentId);

    Integer deleteDepartment(Integer departmentId);

    Integer addDepartment(String departmentName);

    Department getDepartmentByName(String departmentName);

    Integer updateDepartment(@Param("departmentId") Integer departmentId, @Param("departmentName") String departmentName);
}
