package br.com.digital.cp2.entities;

import br.com.digital.cp2.entities.DTO.EmployeeDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity(name = "employee")
@Table(name = "tb_employee")
public class Employee {

    @Id
    @Column(name="employee_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "employee_seq")
    private Long id;

    private String name;
    private String title;
    private BigDecimal salary;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "manager")
    @JsonIgnore
    private Employee manager;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "department")
    private Department department;

    @OneToMany(mappedBy = "manager", cascade = CascadeType.ALL)
    private List<Employee> employeesManaged;

    public Employee() {
    }

    public Employee(EmployeeDTO dto) {
        this.name = dto.name();
        this.title = dto.title();
        this.salary = dto.salary();
        this.manager = dto.manager();
        this.department = dto.department();
        this.employeesManaged = null;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "title = " + title + ", " +
                "salary = " + salary + ", " +
                "manager = " + manager + ", " +
                "department = " + department + ")";
    }
}
