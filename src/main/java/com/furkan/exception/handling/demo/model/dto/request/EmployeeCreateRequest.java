package com.furkan.exception.handling.demo.model.dto.request;

import com.furkan.exception.handling.demo.model.entity.Department;
import jakarta.validation.constraints.*;

public record EmployeeCreateRequest(
        @NotBlank(message = "firstname cannot be blank")
        @Size(min = 2, max = 50 , message = "username must be between 2-50 characters")
        String firstName,

        @NotBlank(message = "email cannot be blank")
        @Email(message = "Enter a valid email address")
        String email,

        //@NotBlank(message = "department cannot be blank")
        Long departmentID,

        @Min(value = 0, message = "Salary must be 0 or greater")
        Double salary,

        @Min(value = 18, message = "Age cannot be lower than 18")
        @Max(value = 100, message = "Age cannot be higher than 100")
        Integer age
) { }
