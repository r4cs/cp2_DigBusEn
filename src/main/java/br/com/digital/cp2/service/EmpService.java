package br.com.digital.cp2.service;

import br.com.digital.cp2.entities.DTO.EmployeeDTO;
import br.com.digital.cp2.entities.Department;
import br.com.digital.cp2.entities.Employee;
import br.com.digital.cp2.repository.DepRepo;
import br.com.digital.cp2.repository.EmpRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpService {


    private final EmpRepo empRepo;
    private final DepRepo depRepo;

    public EmpService(@Autowired EmpRepo empRepo, DepRepo depRepo) {
        this.empRepo = empRepo;
        this.depRepo = depRepo;
    }


    public void cadastrar(EmployeeDTO dto) {
        // Verificar se o manager e o departamento existem no banco de dados
        Optional<Employee> manager = Optional.empty();
        Optional<Department> department = Optional.empty();

        if (dto.manager() != null && dto.manager().getId() != null) {
            manager = empRepo.findById(dto.manager().getId());
        }

        if (dto.department() != null && dto.department().getId() != null) {
            department = depRepo.findById(dto.department().getId());
        }

        if (manager.isPresent() && department.isPresent()) {
            // Caso 2: Ambos manager e department existem
            Employee employee = new Employee(dto);
            employee.setManager(manager.get());
            employee.setDepartment(manager.get().getDepartment());
            empRepo.save(employee);
        } else if (manager.isPresent()) {
            // Caso 1: Apenas manager existe
            Employee employee = new Employee(dto);
            employee.setManager(manager.get());
            employee.setDepartment(manager.get().getDepartment());
            empRepo.save(employee);
        } else if (department.isPresent()) {
            // Caso 3: Apenas o department existe
            Employee managerEntity = new Employee(dto);
            managerEntity.setManager(null);
            managerEntity.setDepartment(department.get());
            empRepo.save(managerEntity);
        } else {
            // Caso 4: Ambos manager e department não existem
            throw new IllegalArgumentException("Não é possível criar um funcionário sem um gerente ou departamento.");
        }
    }


    public Optional<Employee> lerEmployeePorId(Long id) {
        return empRepo.findById(id);
    }

    public List<Employee> lerTodosEmployees() {
        return empRepo.findAll();
    }

    public void atualizarEmp(Long id, Employee novoEmp) {
        Optional<Employee> empAntigo = empRepo.findById(id);

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
        empRepo.deleteById(id);
    }
}
