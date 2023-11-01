package br.com.digital.cp2.entities.DTO;

import br.com.digital.cp2.entities.Department;
import br.com.digital.cp2.entities.Employee;
import java.util.List;

public record DepartmentDTO(
        String name,
        List<Employee> employees
) {
    public DepartmentDTO(Department department) {
        this(
                department.getName(),
                department.getEmployees()
        );
    }
}
