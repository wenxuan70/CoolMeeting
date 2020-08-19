package org.javaboy.meeting.controller;

import org.javaboy.meeting.domain.Department;
import org.javaboy.meeting.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/admin")
public class DepartmentsController {

    @Autowired
    private DepartmentService departmentService;

    @RequestMapping("/departments")
    public String departments(Model model, String error) {
        List<Department> deps = departmentService.getAllDeps();
        model.addAttribute("deps",deps);
        model.addAttribute("error", error);
        return "departments";
    }

    @RequestMapping("/deleteDep")
    public String deleteDep(Integer departmentId) {
        Integer result = departmentService.deleteDepartment(departmentId);
        return "redirect:/admin/departments";
    }

    @PostMapping("/addDepartment")
    public String addDepartment(String departmentName, Model model) {
        if (departmentName == null || departmentName.length() == 0) {
            model.addAttribute("error","部门名称不能为空");
            return "redirect:/admin/departments";
        }
        Integer result = departmentService.addDepartment(departmentName);
        if (result.equals(-1)) {
            model.addAttribute("error","部门名称重复");
        }
        return "redirect:/admin/departments";
    }

    @RequestMapping("/updateDepartment")
    @ResponseBody
    public Map<String,Object> updateDepartment(Integer departmentId, String departmentName) {
        Integer result = departmentService.updateDepartment(departmentId, departmentName);
        Map<String,Object> responseMessage = new LinkedHashMap<>();
        responseMessage.put("code", result);
        responseMessage.put("message",result == 1 ? "修改成功" : "修改失败");
        return responseMessage;
    }
}
