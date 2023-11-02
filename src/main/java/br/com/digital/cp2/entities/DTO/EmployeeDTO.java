package br.com.digital.cp2.entities.DTO;

import br.com.digital.cp2.entities.Department;
import br.com.digital.cp2.entities.Employee;

import java.math.BigDecimal;
import java.util.List;

public record EmployeeDTO(
        Long id,
        String name,
        String title,
        BigDecimal salary,
        Employee manager,
        Department department,
        List<Employee> employeesManaged
) {
    public EmployeeDTO(Employee employee) {
        this(
                employee.getId(),
                employee.getName(),
                employee.getTitle(),
                employee.getSalary(),
                employee.getManager(),
                employee.getDepartment(),
                employee.getEmployeesManaged()
        );
    }
}
