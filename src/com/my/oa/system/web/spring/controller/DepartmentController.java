package com.my.oa.system.web.spring.controller;

import com.my.oa.system.domain.Department;
import com.my.oa.system.service.DepartmentService;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator.
 *
 * @author 吴光辉
 */
@Controller
@RequestMapping("/department")
public class DepartmentController {

    @Resource(name = "departmentService")
    private DepartmentService departmentService;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }

    @RequestMapping("/list")
    public String list(Model model) {
        List<Department> departmentList = this.departmentService.findAllDepartments();
        model.addAttribute("departmentList", departmentList);
        return "department/list";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable Integer id) {
        Department department = this.departmentService.findDepartmentAndChildrenById(id);
        //删除该部门
        this.departmentService.deleteDepartment(department);

        return "redirect:/department/list";
    }

    @RequestMapping(value = "/editUI/{id}", method = RequestMethod.GET)
    public String modify(@PathVariable Integer id, Model model) {
        //查找所有部门
        List<Department> departmentList = this.departmentService.findAllDepartments();
        model.addAttribute("departmentList", departmentList);

        Department department = this.departmentService.findDepartmentById(id);
        model.addAttribute("department", department);

        return "department/editUI";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String modify(Department department, @RequestParam("parentId") int parentId) {
        if (parentId != 0) {
            Department parent = departmentService.findDepartmentById(parentId);
            department.setParent(parent);
        } else {
            department.setParent(null);
        }

        this.departmentService.modifyDepartment(department);
        return "redirect:/department/list";
    }

    @RequestMapping(value = "/addUI", method = RequestMethod.GET)
    public String add(Model model) {
        //查找所有部门
        List<Department> departmentList = this.departmentService.findAllDepartments();
        model.addAttribute("departmentList", departmentList);
        return "department/saveUI";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(Department department, @RequestParam("parentId") int parentId) {

        if (parentId != 0) {
            Department parent = this.departmentService.findDepartmentById(parentId);
            department.setParent(parent);
        } else {
            department.setParent(null);
        }
        this.departmentService.addDepartment(department);

        return "redirect:/department/list";
    }
}
