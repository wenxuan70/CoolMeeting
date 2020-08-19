package org.javaboy.meeting.service;

import org.javaboy.meeting.domain.Department;
import org.javaboy.meeting.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;

    public List<Department> getAllDeps() {
        return departmentMapper.getAllDeps();
    }

    @Transactional
    public Integer deleteDepartment(Integer departmentId) {
        return departmentMapper.deleteDepartment(departmentId);
    }

    @Transactional
    public Integer addDepartment(String departmentName) {
        Department department = departmentMapper.getDepartmentByName(departmentName);
        if (department != null) {
            // 部门名称重复
            return -1;
        }
        return departmentMapper.addDepartment(departmentName);
    }

    @Transactional
    public Integer updateDepartment(Integer departmentId, String departmentName) {
        Department dep = departmentMapper.getDepartmentByName(departmentName);
        if (dep != null) {
            // 部门名称重复
            return -1;
        }
        return departmentMapper.updateDepartment(departmentId, departmentName);
    }
}
