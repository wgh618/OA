package com.my.oa.system.service;

import com.my.oa.system.domain.Department;

import java.util.List;

/**
 * Created by Administrator.
 *
 * @author 吴光辉
 */
public interface DepartmentService {

    public void addDepartment(Department user);

    public List<Department> findAllDepartments();

    public Department findDepartmentById(Integer id);

    public void deleteDepartment(Department user);

    public void modifyDepartment(Department user);

    public Department findDepartmentAndChildrenById(Integer id);
}
