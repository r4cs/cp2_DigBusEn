package br.com.digital.cp2.controller;

import br.com.digital.cp2.entities.DTO.EmployeeDTO;
import br.com.digital.cp2.entities.Employee;
import br.com.digital.cp2.service.EmpService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/employee")
public class EmpController {

    private final EmpService service;

    public EmpController(@Autowired EmpService service) {
        this.service = service;
    }

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid EmployeeDTO dto) {
        System.out.println("Dados Employee: " + dto);
        service.cadastrar(dto);
    }

    @GetMapping(value="/{id}")
    public Optional<Employee> lerEmpPorId(@PathVariable Long id) {
        return service.lerEmployeePorId(id);
    }

    @GetMapping
    public List<Employee> lerTdsEmp() {
        return service.lerTodosEmployees();
    }

    @PatchMapping(value = "/{id}")
    @Transactional
    public void atualizarEmp(@PathVariable Long id, @RequestBody Employee employee) {
        service.atualizarEmp(id, employee);
    }

    @DeleteMapping(value = "/{id}")
    public void delEmp(@PathVariable Long id) {
        service.deletarEmp(id);
    }
}
