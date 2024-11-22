package com.furkan.exception.handling.demo.model.dto.response;

import com.furkan.exception.handling.demo.model.entity.Department;
import jakarta.persistence.*;

public record EmployeeResponse(
         Long id,
         String firstName,
         String email,
         Department department,
         Double salary,
         Integer age
) { }
