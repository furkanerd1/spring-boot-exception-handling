package com.furkan.exception.handling.demo.service;

import com.furkan.exception.handling.demo.exception.BusinessException;
import com.furkan.exception.handling.demo.exception.ErrorCode;
import com.furkan.exception.handling.demo.exception.ResourceNotFoundException;
import com.furkan.exception.handling.demo.model.dto.request.EmployeeCreateRequest;
import com.furkan.exception.handling.demo.model.dto.response.EmployeeResponse;
import com.furkan.exception.handling.demo.model.entity.Employee;
import com.furkan.exception.handling.demo.model.mapper.EmployeeMapper;
import com.furkan.exception.handling.demo.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;


    @Override
    public List<EmployeeResponse> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        if(employees.isEmpty()) {
            throw new  ResourceNotFoundException("There are no employees" , ErrorCode.RESOURCE_NOT_FOUND);
        }
        return employeeMapper.toResponseList(employees);
    }

    @Override
    public EmployeeResponse getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException(("Employee not found , id : "+ employeeId) , ErrorCode.RESOURCE_NOT_FOUND));
        return employeeMapper.toResponse(employee);
    }

    @Override
    @Transactional
    public EmployeeResponse createEmployee(EmployeeCreateRequest request) {
        if(employeeRepository.existsByEmail(request.email())){
          throw new BusinessException("Email already exists: " + request.email() , ErrorCode.RESOURCE_ALREADY_EXISTS);
        }
        Employee employee = employeeMapper.toEntity(request);
        employeeRepository.save(employee);
        return employeeMapper.toResponse(employee);
    }
}
