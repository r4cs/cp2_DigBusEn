package br.com.digital.cp2.service;

import br.com.digital.cp2.entities.DTO.DepartmentDTO;
import br.com.digital.cp2.entities.Department;
import br.com.digital.cp2.repository.DepRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepService {

    private final DepRepo repo;

    public DepService(@Autowired DepRepo repo) {
        this.repo = repo;
    }


    public Optional<Department> cadastrar(DepartmentDTO dto) {
        Department department = new Department(dto);
        repo.save(department);
        return Optional.empty();
    }

    public Optional<Department> lerDepartamentoPorId(Long id) {
        return repo.findById(id);
    }

    public List<Department> lerTodosDepartamentos() {
        return repo.findAll();
    }

    public void atualizarDep(Long id, Department novoDep) {
        Optional<Department> depAntigo = repo.findById(id);

        if (depAntigo.isPresent()) {
            Department department = depAntigo.get();

            if (department.getName() != null) {
                department.setName(novoDep.getName());
            }
            else if (department.getEmployees() != null) {
                department.setEmployees(novoDep.getEmployees());
            } else {
                System.out.println("Algo deu errado");
            }
        }
    }

    public void deletarDep(Long id) {
        repo.deleteById(id);
    }
}
