package com.furkan.exception.handling.demo.model.mapper;

import com.furkan.exception.handling.demo.model.dto.request.EmployeeCreateRequest;
import com.furkan.exception.handling.demo.model.dto.response.EmployeeResponse;
import com.furkan.exception.handling.demo.model.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    @Mapping(source = "departmentID", target = "department.id")
    Employee toEntity(EmployeeCreateRequest request);

    EmployeeResponse toResponse(Employee employee);

    List<EmployeeResponse> toResponseList(List<Employee> employees);


}
