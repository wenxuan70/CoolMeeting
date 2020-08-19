package org.javaboy.meeting.mapper;

import org.apache.ibatis.annotations.Param;
import org.javaboy.meeting.domain.Employee;

import java.util.List;

public interface EmployeeMapper {
    Employee loadEmployeeByUsername(String username);

    Integer addEmployee(Employee employee);

    List<Employee> getEmployeesByStatus(Integer status);

    Integer updateStatus(@Param("employeeId")Integer employeeId, @Param("status") Integer status);

    List<Employee> searchEmployees(@Param("start")Integer start, @Param("size")Integer size, @Param("status")Integer status,
                                   @Param("username")String username, @Param("employeeName")String employeeName);

    Integer getTotal(@Param("status") Integer status, @Param("username") String username,@Param("employeeName") String employeeName);

    List<Employee> getEmployeesByDepId(Integer depId);

    Integer[] getEmployeeIdsByName(String employeeName);

    Integer updatePassword(@Param("username") String username, @Param("password") String password);
}
