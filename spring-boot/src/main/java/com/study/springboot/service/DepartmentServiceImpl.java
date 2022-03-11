package com.study.springboot.service;

import com.study.springboot.entity.Department;
import com.study.springboot.exception.StatusException;
import com.study.springboot.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static com.study.springboot.message.DepartmentExceptionMessage.EXCEPTION_DEPARTMENT_NOT_FOUND;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Component
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository repository;

    @Override
    public Department save(Department department) {
        return repository.save(department);
    }

    @Override
    public List<Department> getAll() {
        return repository.findAll();
    }

    @Override
    public Department getById(Long id) {
        Optional<Department> department = repository.findById(id);

        if (department.isEmpty()){
            throwDepartmentNotFoundException();
        }

        return department.get();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Department putById(Long id, Department dep) {
        Department depFromDB = this.getById(id);

        copyNonNullNorEmpty(depFromDB::setName, dep::getName);
        copyNonNullNorEmpty(depFromDB::setAddress, dep::getAddress);
        copyNonNullNorEmpty(depFromDB::setCode, dep::getCode);

        return repository.save(depFromDB);
    }

    @Override
    public Department getByName(String name) {
        Optional<Department> department = repository.findByNameIgnoreCase(name);

        if (department.isEmpty()){
            throwDepartmentNotFoundException();
        }

        return department.get();
    }

    private boolean isNonNullNorEmpty(String value){
        return Objects.nonNull(value) && !"".equals(value);
    }

    private void throwDepartmentNotFoundException(){
        throw new StatusException(NOT_FOUND, EXCEPTION_DEPARTMENT_NOT_FOUND);
    }

    private void copyNonNullNorEmpty(Consumer<String> setter, Supplier<String> getter){
        String value = getter.get();

        if (isNonNullNorEmpty(value)) {
            setter.accept(value);
        }
    }
}
