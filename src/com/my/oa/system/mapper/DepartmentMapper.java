package com.my.oa.system.mapper;


import com.my.oa.system.domain.Department;

import java.util.List;

/**
 * Created by Administrator.
 *
 * @author 吴光辉
 *
 */
public interface DepartmentMapper {
    public void save(Department user);

    public List<Department> queryAll();

    public Department load(Integer id);

    public void delete(Department user);

    public void update(Department user);

    public Department loadAndChildren(Integer id);
}
