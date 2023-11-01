package br.com.digital.cp2.repository;

import br.com.digital.cp2.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepRepo extends JpaRepository<Department, Long> {
}
