package br.com.digital.cp2.service;

import br.com.digital.cp2.entities.DTO.DepartmentDTO;
import br.com.digital.cp2.entities.Department;
import br.com.digital.cp2.repository.DepRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepService {

    private final DepRepo repo;

    private final EntityManager entityManager;

    public DepService(@Autowired DepRepo repo, EntityManager entityManager) {
        this.repo = repo;
        this.entityManager = entityManager;
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

    public Department findDepartmentByName(String name) {
        String sql = "SELECT d.name FROM tb_department d WHERE d.name = :name";
        TypedQuery<Department> query = entityManager.createQuery(sql, Department.class);
        query.setParameter("name", name);
        List<Department> departments = query.getResultList();
        return departments.isEmpty() ? null : departments.get(0);
    }
}
