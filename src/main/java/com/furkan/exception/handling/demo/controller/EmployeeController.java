package com.furkan.exception.handling.demo.controller;

import com.furkan.exception.handling.demo.model.dto.request.EmployeeCreateRequest;
import com.furkan.exception.handling.demo.model.dto.response.ApiResponse;
import com.furkan.exception.handling.demo.model.dto.response.EmployeeResponse;
import com.furkan.exception.handling.demo.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<EmployeeResponse>>> getAllEmployees(){
        List<EmployeeResponse> employeeResponseList = employeeService.getAllEmployees();
        return ResponseEntity.ok().body(ApiResponse.success(employeeResponseList));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EmployeeResponse>> getEmployee(@PathVariable Long id){
        EmployeeResponse employeeResponse = employeeService.getEmployeeById(id);
        return ResponseEntity.ok().body(ApiResponse.success(employeeResponse));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<EmployeeResponse>> createEmployee(@Valid @RequestBody EmployeeCreateRequest employeeCreateRequest){
        EmployeeResponse employeeResponse = employeeService.createEmployee(employeeCreateRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(employeeResponse));
    }

}
