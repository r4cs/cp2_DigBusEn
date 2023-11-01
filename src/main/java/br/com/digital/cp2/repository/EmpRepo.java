package br.com.digital.cp2.repository;

import br.com.digital.cp2.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpRepo extends JpaRepository<Employee, Long> {
}
