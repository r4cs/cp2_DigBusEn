package br.com.digital.cp2.entities;

import br.com.digital.cp2.entities.DTO.EmployeeDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.awt.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@ToString
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

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "manager")
    @JsonIgnore
    private Employee manager;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "department")
    private Department department;

    @OneToMany(mappedBy = "manager")
    private List<Employee> employeesManaged;

    public Employee() {
    }

    public Employee(EmployeeDTO dto) {
        this.name = dto.name();
        this.title = dto.title();
        this.salary = dto.salary();
        this.manager = dto.manager();
        this.department = dto.department();
        this.employeesManaged = dto.employeesManaged();
    }


}
