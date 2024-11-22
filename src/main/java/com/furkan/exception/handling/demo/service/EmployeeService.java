package com.furkan.exception.handling.demo.service;


import com.furkan.exception.handling.demo.model.dto.request.EmployeeCreateRequest;
import com.furkan.exception.handling.demo.model.dto.response.EmployeeResponse;
import com.furkan.exception.handling.demo.model.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<EmployeeResponse> getAllEmployees();

    EmployeeResponse getEmployeeById(Long employeeId);

    EmployeeResponse createEmployee(EmployeeCreateRequest request);


}
