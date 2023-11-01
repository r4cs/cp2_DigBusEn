package br.com.digital.cp2.service;

import br.com.digital.cp2.entities.DTO.EmployeeDTO;
import br.com.digital.cp2.entities.Employee;
import br.com.digital.cp2.repository.EmpRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpService {


    private final EmpRepo repo;

    public EmpService(@Autowired EmpRepo repo) {
        this.repo = repo;
    }


    public void cadastrar(EmployeeDTO dto) {
        Employee employee = new Employee(dto);
        repo.save(employee);
    }

    public Optional<Employee> lerEmployeePorId(Long id) {
        return repo.findById(id);
    }

    public List<Employee> lerTodosEmployees() {
        return repo.findAll();
    }

    public void atualizarEmp(Long id, Employee novoEmp) {
        Optional<Employee> empAntigo = repo.findById(id);

        if (empAntigo.isPresent()) {
            Employee employee = empAntigo.get();

            if (employee.getName() != null) {
                employee.setName(novoEmp.getName());
            }

            else if (employee.getTitle() != null) {
                employee.setTitle(novoEmp.getTitle());
            }

            else if (employee.getSalary() != null) {
                employee.setSalary(novoEmp.getSalary());
            }

            else if (employee.getManager() != null) {
                employee.setManager(novoEmp.getManager());
            }

            else {
                System.out.println("Algo deu errado");
            }
        }
    }

    public void deletarEmp(Long id) {
        repo.deleteById(id);
    }
}
