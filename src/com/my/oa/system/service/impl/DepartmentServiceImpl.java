package com.my.oa.system.service.impl;

import com.my.oa.system.domain.Department;
import com.my.oa.system.mapper.DepartmentMapper;
import com.my.oa.system.service.DepartmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator.
 *
 * @author 吴光辉
 */
@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {

    @Resource(name = "departmentMapper")
    private DepartmentMapper departmentMapper;

    @Override
    public void addDepartment(Department department) {
        if (department == null) {
            throw new IllegalArgumentException();
        }
        this.departmentMapper.save(department);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<Department> findAllDepartments() {
        return this.departmentMapper.queryAll();
    }

    @Override
    public Department findDepartmentById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("");
        }
        return this.departmentMapper.load(id);
    }

    @Override
    public void deleteDepartment(Department department) {
        if (department == null) {
            throw new IllegalArgumentException("");
        }
        this.departmentMapper.delete(department);
    }

    @Override
    public void modifyDepartment(Department department) {
        if (department == null) {
            throw new IllegalArgumentException("");
        }
        this.departmentMapper.update(department);
    }

    @Override
    public Department findDepartmentAndChildrenById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("");
        }
        return this.departmentMapper.loadAndChildren(id);
    }
}
