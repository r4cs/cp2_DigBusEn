package br.com.digital.cp2.entities;

import br.com.digital.cp2.entities.DTO.DepartmentDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "department")
@Table(name = "tb_department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "department_seq")
    @Column(name="department_id")
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name="employee_id")
    private List<Employee> employees;

    public Department() {
    }

    public Department(DepartmentDTO dto) {
        this.name = dto.name();
        this.employees = dto.employees();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ")";
    }
}
