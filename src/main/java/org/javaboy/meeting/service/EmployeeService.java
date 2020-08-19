package org.javaboy.meeting.service;

import org.javaboy.meeting.domain.Employee;
import org.javaboy.meeting.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    public Employee doLogin(String username, String password) {
        Employee employee =
                employeeMapper.loadEmployeeByUsername(username);
        if (employee == null || !password.equals(employee.getPassword())) {
            return null;
        }
        return employee;
    }

    @Transactional
    public Integer updatePassword(String username, String password) {
        return employeeMapper.updatePassword(username, password);
    }

    @Transactional
    public int addEmployee(Employee employee) {
        Employee employee1 = employeeMapper.loadEmployeeByUsername(employee.getUsername());
        if (employee1 == null) {
            // 待审批
            employee.setStatus(0);
            // 普通用户
            employee.setRole(1);
            return employeeMapper.addEmployee(employee);
        }
        return -1;
    }

    public List<Employee> getEmployeesByStatus(Integer status) {
        return employeeMapper.getEmployeesByStatus(status);
    }

    @Transactional
    public Integer updateStatus(Integer employeeId, Integer status) {
        return employeeMapper.updateStatus(employeeId, status);
    }

    public List<Employee> searchEmployees(Integer index, Integer size, Integer status, String username, String employeeName) {
        Integer start = (index - 1) * size;
        return employeeMapper.searchEmployees(start, size, status, username, employeeName);
    }

    public Integer getTotal(Integer status, String username, String employeeName) {
        return employeeMapper.getTotal(status, username, employeeName);
    }

    public List<Employee> getEmployeesByDepId(Integer depId) {
        return employeeMapper.getEmployeesByDepId(depId);
    }

    /**
     * （模糊查询）通过员工名称的所有员工id
     * @param employeeName
     * @return
     */
    public Integer[] getEmployeeIdsByName(String employeeName) {
        return employeeMapper.getEmployeeIdsByName(employeeName);
    }
}
