package com.study.springboot.service;

import com.study.springboot.entity.Department;

import java.util.List;

public interface DepartmentService {
    Department save(Department department);

    List<Department> getAll();

    Department getById(Long id);

    void deleteById(Long id);
}
